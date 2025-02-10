def levensteinDistance(x, y, z, string1, string2):
    len1 = len(string1)
    len2 = len(string2)
    F = [[(j + i) * x if i * j == 0 else 0 for j in range(len2 + 1)] for i in range(len1 + 1)]
    for j in range(1, len2 + 1):
        F[0][j] = j * y
    delta = min(z, x + y)
    for i in range(1, len1 + 1):
        for j in range(1, len2 + 1):
            if string1[i - 1] == string2[j - 1]:
                F[i][j] = F[i - 1][j - 1]
            else:
                F[i][j] = min(F[i - 1][j] + x, F[i][j - 1] + y, F[i - 1][j - 1] + delta)
    return F[len1][len2]



with open('C:\\Users\\Artem\\Desktop\\in.txt', 'r') as inputFile, open('C:\\Users\\Artem\\Desktop\\out.txt', 'w') as outputFile:
    x = int(inputFile.readline().strip())
    y = int(inputFile.readline().strip())
    z = int(inputFile.readline().strip())
    string1 = inputFile.readline().strip()
    string2 = inputFile.readline().strip()
    outputFile.write(str(levensteinDistance(x, y, z, string1, string2)))