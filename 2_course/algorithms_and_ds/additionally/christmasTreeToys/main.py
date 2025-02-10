with open('C:/Users/Artem/Desktop/input.txt', 'r') as inputFile, open('C:/Users/Artem/Desktop/output.txt', 'w') as outputFile:
    s, n, m = list(map(int, inputFile.readline().split()))
    max_weight = n * m
    set_toys = [0]
    count_set = 0
    answer = []
    matrix = [[0] * (max_weight + 1) for j in range(s + 1)]
    for i in range(s):
        set_toys.append(list(map(int, inputFile.readline().split())))
    for i in range(1, s + 1):
        for j in range(1, max_weight + 1):
            if set_toys[i][0] > j:
                matrix[i][j] = matrix[i-1][j]
            else:
                matrix[i][j] = max(matrix[i-1][j], matrix[i - 1][j - set_toys[i][0]] + set_toys[i][1])
    start_i = s
    count_set = 0
    start_j = max_weight
    answer = ''
    while matrix[start_i][start_j] != 0:
        if matrix[start_i][start_j] != matrix[start_i - 1][start_j]:
            answer = str(start_i) + ' ' + answer
            count_set += 1
            start_j = start_j - set_toys[start_i][0]
        start_i -= 1
    outputFile.write(str(count_set) + "\n" + answer)
        