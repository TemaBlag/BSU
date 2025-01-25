import numpy as np
with open("/Users/user/Library/Mobile Documents/com~apple~TextEdit/Documents/input.txt", "r") as inputFile, open("/Users/user/Documents/output.txt", "w") as outputFile:
    n = int(inputFile.readline().strip())
    p = np.array([0] * (3 * n + 3))
    flag = False
    index = 0
    correct = inputFile.readline().strip()
    findString = inputFile.readline().strip() + "$" + correct + correct
    for i in np.arange(1, 3 * n + 1):
        k = p[i- 1]
        while findString[i] != findString[k] and k > 0:
            k = p[k - 1]
        p[i] = k + 1 if findString[i] == findString[k] else k
        if p[i] == n:
            flag = True
            index = i
            break
    outputFile.write(str(index - 2 * n) if flag else "-1")

