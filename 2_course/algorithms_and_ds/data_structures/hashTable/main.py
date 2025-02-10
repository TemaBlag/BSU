import numpy as np
with open("C:/Users/Artem/Desktop/input.txt") as inputFile, open("C:/Users/Artem/Desktop/output.txt", "w") as outputFile:
    m, c, n = np.array(list(map(int, inputFile.readline().strip().split())))
    hash_table = np.array([-1] * m)
    size = 0
    for i in np.arange(n):
        if size < m:
            val = int(inputFile.readline().strip())
            for k in np.arange(m):
                hash_val = ((val % m) + c * k) % m
                if hash_table[hash_val] == -1 and hash_table[hash_val] != val:
                    break
            if hash_table[hash_val] != val:
                hash_table[hash_val] = val
                size += 1
    outputFile.write(" ".join(map(str, hash_table)))