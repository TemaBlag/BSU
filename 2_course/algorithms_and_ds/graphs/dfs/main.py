def dfs(v):
    global k, labels, visited
    visited[v] = True
    labels[v] = k
    k += 1
    for u in links.get(v, []):
        if not visited[u]:
            dsu(u)


with (open('/Users/user/Documents/input.txt') as inputFile, open('/Users/user/Documents/output.txt', 'w') as outputFile):
    n = int(inputFile.readline().strip())
    links = dict()
    for i in range(n):
        string = inputFile.readline().strip()
        for val in range(2 * n - 1):
            if string[val] == '1':
                links.setdefault(i, []).append(val >> 1)
    k = 1
    labels = [0] * n
    visited = [False] * n
    for i in range(n):
        if not visited[i]:
            dfs(i)
    labels = list(map(str, labels))
    outputFile.write(" ".join(labels))
