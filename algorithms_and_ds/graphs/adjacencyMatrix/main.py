with open('input.txt', 'r') as inputFile, open('output.txt', 'w') as outputFile:
    n, m = list(map(int, inputFile.readline().strip().split()))
    matrix = [[0] * n for _ in range(n)]
    for i in range(m):
        a, b = map(int, inputFile.readline().strip().split())
        matrix[a][b], matrix[b][a] = 1, 1
    for i in range(n):
        for j in range(n):
            outputFile.write(str(matrix[i][j]) + " ")
        outputFile.write("\n")
