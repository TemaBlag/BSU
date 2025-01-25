import bisect
with open('C:/Users/Artem/Desktop/in.txt', 'r') as inputFile, open('C:/Users/Artem/Desktop/out.txt', 'w') as outputFile:
    n = int(inputFile.readline().strip())
    nums = list(map(int, inputFile.readline().strip().split()))
    values = [nums[0]]
    for i in range(1,n):
        x = nums[i]
        index = bisect.bisect_right(values, x)
        if values[index - 1] != x:
            if index == len(values):
                values.append(x)
            else:
                    values[index] = x
    outputFile.write(str(len(values)))


