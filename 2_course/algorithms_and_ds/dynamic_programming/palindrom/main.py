import numpy as np
with open('C:\\Users\\Artem\\Desktop\\input.txt', 'r') as inputFile, open('C:\\Users\\Artem\\Desktop\\output.txt', 'w') as outputFile:
    X = inputFile.readline().strip()
    Y = X[::-1]
    X = ' ' + X
    Y = ' ' + Y
    n = len(X) - 1
    nums = np.array([[0] * (n + 1) for i in range(n + 1)])
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            nums[i][j] = nums[i - 1][j - 1] + 1 if X[i] == Y[j] else max(nums[i - 1][j], nums[i][j - 1])
    lenWord = nums[n][n]
    k = (lenWord + 1) // 2
    flag = lenWord % 2
    index = 0
    i, j = n, n
    word = np.array(['0'] * lenWord)
    while k > 0:
        if X[i] == Y[j]:
            word[index] = X[i]
            word[lenWord - 1 - index] = X[i]
            i -= 1
            j -= 1
            k -= 1
            index += 1
        else:
            if nums[i - 1][j] == nums[i][j]:
                i -= 1
            else:
                j -= 1
    outputFile.write(str(lenWord) + '\n' + ''.join(word))