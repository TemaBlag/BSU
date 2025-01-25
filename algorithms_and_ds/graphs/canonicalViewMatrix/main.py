with open('/Users/user/Documents/input.txt', 'r') as inputFile, open('/Users/user/Documents/output.txt', 'w') as outputFile:
    n = int(inputFile.readline())
    matrix = ['0'] * n
    for i in range(n - 1):
        x, y = inputFile.readline().strip().split()
        matrix[int(y) - 1] = x
    outputFile.write(" ".join(matrix))
