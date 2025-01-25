with open('/Users/user/Documents/input.txt', 'r') as inputFile, open('/Users/user/Documents/output.txt', 'w') as outputFile:
    n = int(inputFile.readline().strip())
    adjacencyList = ['0'] * n
    for i in range(1, n + 1):
        links = inputFile.readline().strip()
        for val in range(2 * n - 1):
            if links[val] == '1':
                adjacencyList[val >> 1] = str(i)
    outputFile.write(" ".join(adjacencyList))
