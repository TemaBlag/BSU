with open('C:/Users/Artem/Desktop/input.txt') as inputFile, open('C:/Users/Artem/Desktop/output.txt', 'w') as outputFile:
    n = int(inputFile.readline().strip())
    index = 0
    while n > 0:
        if n & 1:
            outputFile.write(str(index) + '\n')
        n >>= 1
        index += 1