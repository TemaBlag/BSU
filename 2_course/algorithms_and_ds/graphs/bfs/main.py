def bfs(v, size, matrix, visited):
    global k
    q = []
    q.append(v)
    visited[v] = k
    k += 1
    while len(q) > 0:
        v = q.pop(0)
        for i in range(size):
            if matrix[v][i] and not visited[i]:
                q.append(i)
                visited[i] = k
                k += 1


with open('/Users/user/Documents/input.txt', 'r') as inputFile, open('/Users/user/Documents/output.txt', 'w') as outputFile:
    n = int(inputFile.readline().strip())
    matrix = []
    for i in range(n):
        matrix.append(list(map(int, inputFile.readline().strip().split())))
    visited = [0] * n
    k = 1
    for val in range(n):
        if not visited[val]:
            bfs(val, n, matrix, visited)
    visited = list(map(str, visited))
    outputFile.write(' '.join(visited))

