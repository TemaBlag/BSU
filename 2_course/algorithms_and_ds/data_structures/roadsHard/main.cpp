#include <iostream>
#include <fstream>

long long roads[750003][3];
long long parent[750003];
long long size[750003];
long long requests[750003];
long long con_com = 0;

class DSU {
public:
    DSU(long long n) {
        for (long long i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        con_com = n;
    }

    long long findSet(long long x) {
        if (parent[x] == x) {
            return x;
        }
        else {
            parent[x] = findSet(parent[x]);
            return parent[x];
        }
    }

    void unionSets(long long x, long long y) {
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
    std::ifstream inputFile("C:\\Users\\Artem\\Desktop\\input.txt");
    std::ofstream outputFile("C:\\Users\\Artem\\Desktop\\output.txt");

    long long n, m ,q;
    inputFile >> n >> m >> q;

    DSU dsu(n);
    long long k = 0;

    for (long long i = 0; i < m; i++) {
        long long x, y;
        inputFile >> x >> y;
        roads[i][0] = x-1;
        roads[i][1] = y-1;
        roads[i][2] = 1;
    }

    for (long long i = 0; i < q; i++) {
        long long x;
        inputFile >> x;
        requests[i] = x - 1;
        roads[x-1][2] = 0;
    }

    for (long long i = 0; i < m; i++) {
        if (roads[i][2])
            dsu.unionSets(roads[i][0], roads[i][1]);
    }

    for (long long i = q; i > 0; i--) {
        if (con_com == 1) {
            k = i;
            break;
        }
        dsu.unionSets(roads[requests[i-1]][0], roads[requests[i-1]][1]);
    }

    for (long long i = 0; i < k; i++) {
        outputFile << 1;
    }

    for (long long i = k; i < q; i++) {
        outputFile << 0;
    }

    inputFile.close();
    outputFile.close();

    return 0;
}
