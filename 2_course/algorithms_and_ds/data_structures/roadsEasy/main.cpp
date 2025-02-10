#include <iostream>
#include <fstream>
#include <vector>

class DSU {
public:
    std::vector<int> parent;
    std::vector<int> size;
    int con_com = 0;

    DSU(int n): parent(n), size(n, 1) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        con_com = n;
    }

    int findSet(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = findSet(parent[x]);
    }

    void unionSets(int x, int y) {
        x = findSet(x);
        y = findSet(y);
        if (x != y) {
            con_com--;
            if (size[x] < size[y])
                std::swap(x, y);
            parent[y] = x;
            size[x] += size[y];
        }
    }
};

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    std::ifstream inputFile("C:\\Users\\Artem\\Desktop\\input.txt");
    std::ofstream outputFile("C:\\Users\\Artem\\Desktop\\output.txt");
    int n, m;
    inputFile >> n >> m;
    DSU dsu(n);
    for (int i = 0; i < m; i++) {
        int x, y;
        inputFile >> x >> y;
        dsu.unionSets(x, y);
        outputFile << dsu.con_com << std::endl;
    }
    inputFile.close();
    outputFile.close();
    return 0;
}
