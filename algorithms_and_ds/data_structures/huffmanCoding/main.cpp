#include <iostream>
#include <fstream>
#include <vector>
long long nums[2500003];
std::vector<long long> sums= {0};
int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::ifstream inputFile("C:/Users/Artem/Desktop/huffman.in.txt");
    std::ofstream outputFile("C:/Users/Artem/Desktop/huffman.out.txt");
    long long n, max_val = 1e9 + 1;
    inputFile >> n ;
    for (long long i = 0; i < n; i++)
         inputFile >> nums[i];
    sums[0] = nums[0] + nums[1];
    long long answer = sums[0];
    long  long index_nums = 2;
    long long index_sums = 0;
    while (index_nums < n - 1) {
        long long val = sums.size() == index_sums + 1 ? max_val : sums[index_sums + 1];
        long long val_1 = nums[index_nums] + nums[index_nums + 1];
        long long val_2 = nums[index_nums] + sums[index_sums];
        long long val_3 = sums[index_sums] + val;
        if (val_1 <= val_2 && val_1 <= val_3) {
            sums.push_back(val_1);
            answer += val_1;
            index_nums += 2;
        } else if (val_2 <= val_1 && val_2 <= val_3) {
            sums.push_back(val_2);
            index_sums += 1;
            index_nums += 1;
            answer += val_2;
        } else {
            sums.push_back(val_3);
            index_sums += 2;
            answer += val_3;
        }
    }
    while (index_nums < n) {
        long long val = sums.size() == index_sums + 1 ? max_val : sums[index_sums + 1];
        long long val_2 = nums[index_nums] + sums[index_sums];
        long long val_3 = sums[index_sums] + val;
        if (val_2 <= val_3) {
            sums.push_back(val_2);
            index_sums += 1;
            answer += val_2;
            index_nums += 1;
        } else {
        sums.push_back(val_3);
        index_sums += 2;
        answer += val_3;
        }
    }
    if (index_sums != sums.size() - 1) {
        while (index_sums != sums.size() - 1) {
        answer += sums[index_sums] + sums[index_sums + 1];
        sums.push_back(sums[index_sums] + sums[index_sums + 1]);
        index_sums += 2;
         }
    }
    outputFile << answer;
    return 0;
}