with open('C:/Users/Artem/Desktop/bst.in.txt') as inputFile, open('C:/Users/Artem/Desktop/bst.out.txt', 'w') as outputFile:
    n = int(inputFile.readline().strip())
    root = int(inputFile.readline().strip())
    tops = [root]
    flag = True
    for i in range(1, n):
        tops.append(inputFile.readline().strip().split())
        if tops[i][2] == "L":
            if int(tops[i][0]) >= int(tops[int(tops[i][1])][0]):
                flag = False
        else:
            if int(tops[i][0]) < int(tops[int(tops[i][1])][0]):
                flag = False
    outputFile.write("YES" if flag else "NO")