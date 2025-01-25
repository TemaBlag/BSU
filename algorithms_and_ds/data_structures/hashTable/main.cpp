#include <iostream>
#include <fstream>

long long hash_table[10003];

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::ifstream inputFile("C:\\Users\\Artem\\Desktop\\input.txt");
    std::ofstream outputFile("C:\\Users\\Artem\\Desktop\\output.txt");
    long long m, c, n;
    inputFile >> m >> c >> n;
    for (long long i = 0; i < m; i++)
        hash_table[i] = -1;
    long long size = 0;
    for (long long i = 0; i < n && size < m; i++) {
        long long k = 0, val;
        inputFile >> val;
        long long hash_val = ((val % m) + c * k) % m;
        while (hash_table[hash_val] != -1 && hash_table[hash_val] != val) {
            k++;
            hash_val = ((val % m) + c * k) % m;
        }
        if (hash_table[hash_val] != val) {
            hash_table[hash_val] = val;
            size++;
        }
    }
    for (long long i = 0; i < m; i++)
        outputFile << hash_table[i] << " ";
    return 0;
}
