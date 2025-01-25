import sys

sys.setrecursionlimit(100000)
topsorted = []
def dfs(v):
    visited[v - 1] = True
    for u in dependencies.get(v, []):
        if not visited[u - 1]:
            dfs(u)
    topsorted.append(v)


with open('/Users/user/Downloads/Telegram Desktop/input-8.txt', 'r') as inputFile, open('/Users/user/Documents/output.txt', 'w') as outputFile:
    dependencies, freedom = dict(), dict()
    index, maxFine, time = 0, 0, 0
    n = int(inputFile.readline().strip())
    matrix, k = [0] * n, 0
    for i in range(n):
        matrix[i] = [i + 1] + list(map(int, inputFile.readline().strip().split()))
    m = int(inputFile.readline().strip())
    for i in range(m):
        x, y = list(map(int, inputFile.readline().strip().split()))
        dependencies.setdefault(y, []).append(x)
    arr = matrix[:]
    matrix.sort(key=lambda x: x[2])
    visited = [False] * n
    for val in matrix:
        if not visited[val[0] - 1]:
            dfs(val[0])
    index, maxFine, time = topsorted[-1], 0, 0
    for i in range(n):
        time += arr[topsorted[i] - 1][1]
        if maxFine < time - arr[topsorted[i] - 1][2]:
            maxFine, index = time - arr[topsorted[i] - 1][2], topsorted[i]
    topsorted = list(map(str, topsorted))
    outputFile.write(f"{index} {maxFine}\n")
    outputFile.write("\n".join(topsorted))

