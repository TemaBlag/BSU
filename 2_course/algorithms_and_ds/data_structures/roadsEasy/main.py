import numpy as np


class DSU:
    def __init__(self, n):
        self.parent = [-1 for _ in np.arange(n + 1)]
        self.con_com = n  # count_connectivity_component

    def findSet(self, x):
        if self.parent[x] < 0:
            return x
        else:
            self.parent[x] = self.findSet(self.parent[x])
        return self.parent[x]

    def union(self, x, y):
        x_p = self.findSet(x)
        y_p = self.findSet(y)
        size_x, size_y = np.abs(self.parent[x_p]), np.abs(self.parent[y_p])
        if x_p != y_p:
            self.con_com -= 1
            if size_x > size_y:
                self.parent[x_p] = - (size_x + size_y)
                self.parent[y_p] = x_p
            else:
                self.parent[y_p] = - (size_x + size_y)
                self.parent[x_p] = y_p


with open('C:\\Users\\Artem\\Desktop\\input.txt', 'r') as inputFile, open('C:\\Users\\Artem\\Desktop\\output.txt','w') as outputFile:
    n, m = list(map(int, inputFile.readline().strip().split()))
    dsu = DSU(n)
    string = "1\n"
    k = 0
    for i in np.arange(m):
        dsu.union(*list(map(int, inputFile.readline().strip().split())))
        if dsu.con_com == 1:
            k = i
            break
        outputFile.write(str(dsu.con_com) + "\n")
    for i in np.arange(m - k):
        outputFile.write(string)
