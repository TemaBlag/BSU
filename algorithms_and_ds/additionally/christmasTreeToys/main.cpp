#include <iostream>
#include <fstream>
#include <vector>

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::ifstream inputFile("input.txt");
    std::ofstream outputFile("output.txt");

    long long s, n, m;
    inputFile >> s >> n >> m;
    long long max_weight = n * m;
    std::vector<long long> set_toys(1);
    long long count_set = 0;
    std::vector<long long> answer;
    std::vector<std::vector<long long>> matrix(s + 1, std::vector<long long>(max_weight + 1, 0));

    for (long long i = 0; i < s; i++) {
        long long toy_weight, toy_value;
        inputFile >> toy_weight >> toy_value;
        set_toys.push_back(toy_weight);
        set_toys.push_back(toy_value);
    }

    for (long long i = 1; i <= s; i++) {
        for (long long j = 1; j <= max_weight; j++) {
            if (set_toys[i * 2 - 1] > j) {
                matrix[i][j] = matrix[i - 1][j];
            } else {
                matrix[i][j] = std::max(matrix[i - 1][j], matrix[i - 1][j - set_toys[i * 2 - 1]] + set_toys[i * 2]);
            }
        }
    }

    long long start_i = s;
    long long start_j = max_weight;
    while (matrix[start_i][start_j] != 0) {
        if (matrix[start_i][start_j] != matrix[start_i - 1][start_j]) {
            answer.push_back(start_i);
            count_set++;
            start_j = start_j - set_toys[start_i * 2 - 1];
        }
        start_i--;
    }

    outputFile << count_set << "\n";
    for (long long i = answer.size() - 1; i >= 0; i--) {
        outputFile << answer[i] << " ";
    }

    return 0;
}