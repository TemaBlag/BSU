#include <iostream>
#include <string>
#include <map>
#include <vector>
#include <fstream>
#include <sstream>

long matrix[10][10] = {1};

std::string canadianAirlines(int n, std::map<std::string, std::vector<int>> edges) {
    for (int i = 1; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            long val = (i == j) ? 0 : 1;
            if (edges.find(std::to_string(i)) != edges.end()) {
                long max_val = -1000001;
                for (int k : edges[std::to_string(i)]) {
                    if (matrix[k][j] > max_val) {
                        if (k != j) max_val = matrix[k][j];
                        if (k + j == 0) max_val = 1;
                    }
                }
                val += max_val;
            } else val = -1000001;
            matrix[i][j] = val;
            matrix[j][i] = val;
        }
    };
    return (matrix[n - 1][n - 1] > 0) ? std::to_string(matrix[n - 1][n - 1]) : "No solution";
}


int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::string line = "";
    std::map<std::string, int> points;
    std::map<std::string, std::vector<int>> edges;
    std::ifstream inputFile("C:\\Users\\Artem\\Desktop\\in.txt");
    std::getline(inputFile, line);
    std::istringstream iss(line);
    int n, m;
    iss >> n;
    iss >> m;
    for (int i = 0; i < n; i++){
        std::getline(inputFile, line);
        points[line] = i;
    }
    for (int i = 0; i < m; i++){
        std::getline(inputFile, line);
        int index = line.find(' ');
        std::string val1 = line.substr(0, index);
        std::string val2 = line.substr(index + 1, line.length() - 1);
        if (points[val1] > points[val2])
            edges[std::to_string(points[val1])].push_back(points[val2]);
        else
            edges[std::to_string(points[val2])].push_back(points[val1]);
    }
    inputFile.close();
    std::ofstream out("C:\\Users\\Artem\\Desktop\\out.txt", std::ios::app);
    if (out.is_open()) out <<canadianAirlines(n, edges);
    out.close();
}
