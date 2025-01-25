#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_map>
#include <algorithm>

std::unordered_map<int, std::vector<int>> dependencies;
std::vector<int> topsorted;
std::vector<bool> visited;

void dfs(int v) {
    visited[v - 1] = true;
    for (int u : dependencies[v]) {
        if (!visited[u - 1]) {
            dfs(u);
        }
    }
    topsorted.push_back(v);
}

int main() {
    std::ifstream inputFile("/Users/user/Downloads/Telegram Desktop/input-8.txt");
    std::ofstream outputFile("/Users/user/Documents/output.txt");

    int n;
    inputFile >> n;

    std::vector<std::vector<int>> matrix(n);
    for (int i = 0; i < n; i++) {
        matrix[i].resize(3);
        matrix[i][0] = i + 1;
        inputFile >> matrix[i][1] >> matrix[i][2];
    }

    int m;
    inputFile >> m;
    for (int i = 0; i < m; i++) {
        int x, y;
        inputFile >> x >> y;
        dependencies[y].push_back(x);
    }

    std::vector<std::vector<int>> arr = matrix;
    std::sort(matrix.begin(), matrix.end(), [](const std::vector<int>& a, const std::vector<int>& b) {
        return a[2] < b[2];
    });

    visited.assign(n, false);
    for (const auto& val : matrix) {
        if (!visited[val[0] - 1]) {
            dfs(val[0]);
        }
    }

    int index, maxFine, time;
    index = topsorted.back();
    maxFine = time = 0;

    for (int i = 0; i < n; i++) {
        time += arr[topsorted[i] - 1][1];
        if (maxFine < time - arr[topsorted[i] - 1][2]) {
            maxFine = time - arr[topsorted[i] - 1][2];
            index = topsorted[i];
        }
    }

    outputFile << index << " " << maxFine << "\n";
    for (int i = 0; i < n; i++) {
        outputFile << topsorted[i] << "\n";
    }

    return 0;
}
