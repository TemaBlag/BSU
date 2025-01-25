/*#include <iostream>
#include "string"
#include <vector>

int main() {
    std::string str;
    std::cin >> str;
    int size = str.length();
    std::vector<std::pair<std::pair<int, int>, int>> temp(size);
    for (int i = 1; i < size; i <<= 1) {
        for (int j = 0; j < size; j++)
            temp[j] = { { str[j], str[(j + i) % size] }, j };
        sort(temp.begin(), temp.end());
        int clas = 0;
        for (int k = 0; k < size; ++k) {
            if (k && temp[k].first != temp[k-1].first)
                clas++;
            str[temp[k].second] = clas;
        }
    }
    int p[size];
    for (int i = 0; i < size; i++)
        p[str[i]] = i;
    std::cout << size << std::endl;
    for (int val : p)
        std::cout << val + 1 << " ";
    return 0;
}

#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <map>


int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::string inputStr;
    std::cin >> inputStr;

    int size = inputStr.length();
    std::byte strings[size];
    int del;
    std::set<std::byte> setString;
    for (int i = 0; i < size; i++) {
        if (inputStr[i] >= '0' && inputStr[i] <= '9') {
            del = 48;
        } else if (inputStr[i] >= 'A' && inputStr[i] <= 'Z') {
            del = 55;
        } else {
            del = 61;
        }
        strings[i] = (std::byte)(int(inputStr[i]) - del);
        setString.insert(strings[i]);
    }
    std::cout << size << std::endl;
    for (std::byte value: setString) {
        std::map<std::vector<std::byte>, bool> arrValue;
        for (int i = 0; i < size; i++) {
            if (strings[i] == value) {
                std::vector<std::byte> arr;
                for (int j = i; j < size; j++)
                    arr.push_back(strings[j]);
                arrValue.insert({arr, true});
            }
        }
        for (std::pair<std::vector<std::byte>, bool> val: arrValue)
            std::cout << size - val.first.size() + 1 << " ";
    }
    return 0;







*/
#include <iostream>
#include "string"
#include <vector>

int main() {
    std::string s;
    std::cin >> s;
    s += ('0' - 1);
    int size = s.length();
    std::vector<int> p(size, 0), c(size, 0), cnt(76, 0);
    std::vector<int> str(size);
    for (int i = 0; i < size; i++)
        str[i] = s[i] - '0' + 1;
    for (int i = 0; i < size; i++)
        cnt[str[i]] += 1;
    for (int i = 1; i < 76; i++)
        cnt[i] += cnt[i - 1];
    for (int i = 0; i < size; i++){
        --cnt[str[i]];
        p[cnt[str[i]]] = i;
    }
    int clas = 1;
    c[p[0]] = 0;
    for (int i = 1; i < size; i++) {
        if (str[p[i]] != str[p[i-1]])
            ++clas;
        c[p[i]] = clas - 1;
    }
    std::vector<int> pn(size, 0), cn(size, 0);
    for (int i = 0; ( 1 << i) < size; i++) {
        for (int j = 0; j < size; j++) {
            pn[j] = p[j] - (1 << i);
            if (pn[j] < 0)
                pn[j] += size;
        }
        std::vector<int> cnt1(clas, 0);
        for (int k = 0; k < size; k++)
            ++cnt1[c[pn[k]]];
        for (int l = 1; l < clas; l++)
            cnt1[l] += cnt1[l - 1];
        for (int g = size - 1; g >= 0; --g)
            p[--cnt1[c[pn[g]]]] = pn[g];
        cn[p[0]] = 0;
        clas = 1;
        for (int j = 1; j < size; j++) {
            int mid1 = (p[j] + (1 << i)) % size,  mid2 = (p[j - 1] + (1 << i)) % size;
            if (c[p[j]] != c[p[j - 1]] || c[mid1] != c[mid2])
                ++clas;
            cn[p[j]] = clas - 1;
        }
        for (int i = 0; i < size; i++)
            c[i] = cn[i];
    }
    std::cout << size - 1 << std::endl;
    for (int i = 0; i < size; i++)
        if (p[i] != size - 1)
            std::cout << p[i] + 1 << " ";
    return 0;
}





