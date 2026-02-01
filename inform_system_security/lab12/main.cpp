#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>
#include <cstring>
#include <sys/stat.h>
#include <unistd.h>
#include <limits.h>
#include <mach-o/dyld.h>
#include <CoreFoundation/CoreFoundation.h>
#include <IOKit/IOKitLib.h>
#include <CommonCrypto/CommonHMAC.h>

const char FOOTER_MAGIC[] = "MYLOCK1!";
const size_t FOOTER_MAGIC_LEN = 8;
const size_t FOOTER_LEN = FOOTER_MAGIC_LEN + 4;
const size_t HMAC_LEN = 32;

std::string getExecutablePath() {
    uint32_t bufsize = 0;
    _NSGetExecutablePath(nullptr, &bufsize);
    std::string buf;
    buf.resize(bufsize);
    if (_NSGetExecutablePath(&buf[0], &bufsize) != 0) {
        throw std::runtime_error("Cannot get executable path");
    }

    char realbuf[PATH_MAX];
    if (realpath(buf.c_str(), realbuf) == nullptr) {
        return buf;
    }
    return std::string(realbuf);
}

std::string getFileName(const std::string &path) {
    auto pos = path.find_last_of('/');
    if (pos == std::string::npos) return path;
    return path.substr(pos + 1);
}

std::string getDirPath(const std::string &path) {
    auto pos = path.find_last_of('/');
    if (pos == std::string::npos) return ".";
    return path.substr(0, pos);
}

std::string getMachineUUID() {
    io_service_t platformExpert = IOServiceGetMatchingService(kIOMasterPortDefault, IOServiceMatching("IOPlatformExpertDevice"));
    if (!platformExpert) return std::string();
    CFTypeRef uuidCF = IORegistryEntryCreateCFProperty(platformExpert, CFSTR("IOPlatformUUID"), kCFAllocatorDefault, 0);
    IOObjectRelease(platformExpert);
    if (!uuidCF) return std::string();
    CFStringRef uuidStr = (CFStringRef)uuidCF;
    char buf[256];
    Boolean ok = CFStringGetCString(uuidStr, buf, sizeof(buf), kCFStringEncodingUTF8);
    CFRelease(uuidCF);
    if (!ok) return std::string();
    return std::string(buf);
}

std::vector<unsigned char> hmac_sha256(const std::string &key, const std::string &data) {
    unsigned char mac[CC_SHA256_DIGEST_LENGTH];
    CCHmac(kCCHmacAlgSHA256, key.data(), key.size(), data.data(), data.size(), mac);
    return std::vector<unsigned char>(mac, mac + CC_SHA256_DIGEST_LENGTH);
}

std::string make_payload_json(const std::string &exe_name, const std::string &dir, const std::string &abs_path, const std::string &machine_uuid) {
    std::ostringstream ss;
    ss << "{";
    ss << "\"exe_name\":\"" << exe_name << "\",";
    ss << "\"dir\":\"" << dir << "\",";
    ss << "\"abs_path\":\"" << abs_path << "\",";
    ss << "\"machine_uuid\":\"" << machine_uuid << "\"";
    ss << "}";
    return ss.str();
}

bool read_embedded_block(const std::string &exe_path, std::string &out_payload, std::vector<unsigned char> &out_mac) {
    std::ifstream ifs(exe_path, std::ios::binary);
    if (!ifs) return false;
    ifs.seekg(0, std::ios::end);
    std::streamoff fsize = ifs.tellg();
    if (fsize < (std::streamoff) (FOOTER_LEN + HMAC_LEN)) return false;

    ifs.seekg(fsize - (std::streamoff)FOOTER_LEN, std::ios::beg);
    char magic_buf[FOOTER_MAGIC_LEN];
    ifs.read(magic_buf, FOOTER_MAGIC_LEN);
    uint32_t payload_len = 0;
    ifs.read(reinterpret_cast<char*>(&payload_len), sizeof(uint32_t));

    if (memcmp(magic_buf, FOOTER_MAGIC, FOOTER_MAGIC_LEN) != 0) return false;

    std::streamoff total = (std::streamoff)payload_len + (std::streamoff)HMAC_LEN + (std::streamoff)FOOTER_LEN;
    if (fsize < total) return false;

    ifs.seekg(fsize - total, std::ios::beg);
    std::vector<char> payload_buf(payload_len);
    ifs.read(payload_buf.data(), payload_len);
    std::vector<unsigned char> mac_buf(HMAC_LEN);
    ifs.read(reinterpret_cast<char*>(mac_buf.data()), HMAC_LEN);

    out_payload.assign(payload_buf.begin(), payload_buf.end());
    out_mac = mac_buf;
    return true;
}

bool append_embedded_block(const std::string &exe_path, const std::string &payload, const std::vector<unsigned char> &mac) {
    std::ofstream ofs(exe_path, std::ios::binary | std::ios::app);
    if (!ofs) return false;
    ofs.write(payload.data(), payload.size());
    ofs.write(reinterpret_cast<const char*>(mac.data()), mac.size());
    ofs.write(FOOTER_MAGIC, FOOTER_MAGIC_LEN);
    uint32_t payload_len = (uint32_t)payload.size();
    ofs.write(reinterpret_cast<const char*>(&payload_len), sizeof(uint32_t));
    ofs.flush();
    return true;
}

std::string extract_json_string(const std::string &json, const std::string &key) {
    std::string q = "\"" + key + "\":\"";
    auto p = json.find(q);
    if (p == std::string::npos) return {};
    p += q.size();
    auto qpos = json.find('"', p);
    if (qpos == std::string::npos) return {};
    return json.substr(p, qpos - p);
}

int main() {
    try {
        std::string exe_path = getExecutablePath();
        std::string exe_name = getFileName(exe_path);
        std::string dir = getDirPath(exe_path);
        std::string machine_uuid = getMachineUUID();

        if (machine_uuid.empty()) {
            std::cerr << "Cannot determine machine UUID. Abort.\n";
            return 2;
        }
        const std::string hmac_key = machine_uuid + "|SOME_STATIC_SALT_v1|";

        std::string payload;
        std::vector<unsigned char> mac;
        bool has = read_embedded_block(exe_path, payload, mac);

        if (!has) {
            std::cout << "Первый запуск — инициализация привязки.\n";
            std::string payload_json = make_payload_json(exe_name, dir, exe_path, machine_uuid);
            auto mac_calc = hmac_sha256(hmac_key, payload_json);
            bool ok = append_embedded_block(exe_path, payload_json, mac_calc);
            if (!ok) {
                std::cerr << "Не удалось записать данные в исполняемый файл. Проверьте права.\n";
                return 3;
            }
            std::cout << "Инициализация завершена. Программа привязана к:\n";
            std::cout << "  machine_uuid=" << machine_uuid << "\n";
            std::cout << "  exe_name=" << exe_name << "\n";
            std::cout << "  dir=" << dir << "\n";
            std::cout << "Продолжение работы программы...\n";
            return 0;
        } else {
            auto mac_calc = hmac_sha256(hmac_key, payload);
            if (mac_calc != mac) {
                std::cerr << "Контрольная подпись не совпадает. Бинарь испорчен или скопирован.\n";
                return 4;
            }

            std::string stored_name = extract_json_string(payload, "exe_name");
            std::string stored_dir = extract_json_string(payload, "dir");
            std::string stored_path = extract_json_string(payload, "abs_path");
            std::string stored_uuid = extract_json_string(payload, "machine_uuid");

            bool ok = true;
            if (stored_uuid != machine_uuid) {
                std::cerr << "Неверный компьютер.\n";
                ok = false;
            }
            if (stored_name != exe_name) {
                std::cerr << "Неверное имя файла. Ожидалось: " << stored_name << " , текущее: " << exe_name << "\n";
                ok = false;
            }
            if (stored_dir != dir) {
                std::cerr << "Неверная папка. Ожидалось: " << stored_dir << " , текущая: " << dir << "\n";
                ok = false;
            }
            if (!ok) {
                std::cerr << "Запуск запрещён.\n";
                return 5;
            }
            std::cout << "Проверка пройдена\n";
            return 0;
        }

    } catch (const std::exception &ex) {
        std::cerr << "Exception: " << ex.what() << "\n";
        return 10;
    }
}
