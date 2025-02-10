with open("C:/Users/Artem/Desktop/input.txt") as input_file, open("C:/Users/Artem/Desktop/output.txt", "w") as output_file:
    n = int(input_file.readline().strip())
    matrix = [[0] * n for i in range(n)]
    for i in range(n):
        matrix[i][i] = list(map(int, input_file.readline().strip().split())) + [0]
    for i in range(1, n):
        for j in range(n - i):
            matrix[j][j + i] = [1, 1, float('inf')]
            for k in range(j, i + j):
                temp = matrix[j][k][2] + matrix[k+1][i + j][2] + matrix[j][k][0] * matrix[j][k][1] * matrix[k+1][i + j][1]
                if temp < matrix[j][j + i][2]:
                    matrix[j][j + i][0] = matrix[j][k][0]
                    matrix[j][j + i][1] = matrix[k+1][i + j][1]
                    matrix[j][j + i][2] = temp
    output_file.write(str(matrix[0][n-1][2]))


