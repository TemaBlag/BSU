#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <numeric>
long nums[7000+1][7000+1];
int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::string X = "";
    std::ifstream in("C:\\Users\\Artem\\Desktop\\input.txt");
    if (in.is_open()) std::getline(in, X);
    else in.close();
    std::string Y = X;
    std::reverse(Y.begin(), Y.end());
    X = ' ' + X;
    Y = ' ' + Y;
    long long n = X.length() - 1;
    for (int i = 1; i <= n; ++i){
        for (int j = 1; j <= n; ++j){
            if (X[i] == Y[j]) nums[i][j] = nums[i - 1][j - 1] + 1;
            else nums[i][j] = std::max(nums[i - 1][j], nums[i][j - 1]);
        }
    }
    long long lenWord = nums[n][n];
    long long k = (lenWord + 1) / 2;
    long long i = n, j = n;
    std::string word[lenWord];
    long long index = 0;
    while (k) {
        if (X[i] == Y[j]) {
            word[index] = X[i];
            word[lenWord - 1 - index] = X[i];
            i -= 1;
            j -= 1;
            k -= 1;
            index += 1;
        }
        else {
            if (nums[i - 1][j] == nums[i][j]) i -= 1;
            else j -= 1;
        }
    };
    int size = sizeof(word) / sizeof(word[0]);
    std::string str = std::accumulate(word, word + size, std::string());
    std::ofstream out("C:\\Users\\Artem\\Desktop\\output.txt", std::ios::app);
    if (out.is_open()) out << lenWord << "\n" << str << std::endl;
    out.close();
}
