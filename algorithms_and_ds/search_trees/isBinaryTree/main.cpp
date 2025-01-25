#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
#include <limits>

std::ifstream inputFile("C:/Users/Artem/Desktop/bst.in.txt");
std::ofstream outputFile("C:/Users/Artem/Desktop/bst.out.txt");
long long tops[800001][3] = {0};

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    long long n, root;
    long long minValue = std::numeric_limits<long long>::min();
    long long maxValue = std::numeric_limits<long long>::max();
    inputFile >> n;
    inputFile >> root;
    tops[0][0] = minValue;
    tops[0][1] = root;
    tops[0][2] = maxValue;
    bool flag = true;
    for (long i = 0; i < n; i++) {
        std::string line;
        std::getline(inputFile, line);
        std::stringstream ss(line);
        std::string subtree;
        long val, num;
        ss >> val >> num >> subtree;
        if (subtree == "L") {
            if (!(tops[num - 1][0] < val and val < tops[num - 1][1])){
                flag = false;
            }
            tops[i][0] = tops[num - 1][0];
            tops[i][1] = val;
            tops[i][2] = tops[num - 1][1];
        }
        if (subtree == "R") {
            if (!(tops[num - 1][1] <= val and val < tops[num - 1][2])){
                flag = false;
            }
            tops[i][0] = tops[num - 1][1];
            tops[i][1] = val;
            tops[i][2] = tops[num - 1][2];
        }
    }
    outputFile << (flag ? "YES" : "NO");
}
