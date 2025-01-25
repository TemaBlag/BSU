def canadianAirlines(n, edges):
    matrix = [[1] * n for i in range(n)]
    for i in range(1, n):
        for j in range(i + 1):
            val = 0 if i == j else 1
            if len(edges.get(str(i), [])):
                max_val = -1000001
                for k in edges[str(i)]:
                    if matrix[k][j] > max_val:
                        if k != j:
                            max_val = matrix[k][j]
                        if k + j == 0:
                            max_val = 1
                val += max_val
            else:
                val = -1000001
            matrix[i][j] = val
            matrix[j][i] = val
    return matrix[n - 1][n - 1] if matrix[n - 1][n - 1] > 0 else 'No solution'


with open('C:\\Users\\Artem\\Desktop\\in.txt', 'r') as inputFile, open('C:\\Users\\Artem\\Desktop\\out.txt', 'w') as outputFile:
    n, m = list(map(int, inputFile.readline().strip().split()))
    points, edges = {}, {}
    for i in range(n):
        points[inputFile.readline().strip()] = i
    for _ in range(m):
        val1, val2 = inputFile.readline().strip().split()
        if points[val1] > points[val2]:
            edges[str(points[val1])] = edges.get(str(points[val1]), []) + [points[val2]]
        else:
            edges[str(points[val2])] = edges.get(str(points[val2]), []) + [points[val1]]
    outputFile.write(str(canadianAirlines(n, edges)))










