with open('/Users/user/Documents/input.txt', 'r') as inputFile, open('/Users/user/Documents/output.txt', 'w') as outputFile:
    n, m = list(map(int, inputFile.readline().strip().split()))
    adjList = dict()
    for i in range(m):
        x, y = inputFile.readline().strip().split()
        adjList.setdefault(x, []).append(y)
        adjList.setdefault(y, []).append(x)
    for x in range(1, n + 1):
        val = adjList.get(str(x))
        outputFile.write(str(len(val)) + " " + " ".join(val) + "\n" if val else "0\n")
