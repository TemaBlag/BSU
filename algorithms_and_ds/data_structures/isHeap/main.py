with open('C:/Users/Artem/Desktop/input.txt') as inputFile, open('C:/Users/Artem/Desktop/output.txt', 'w') as outputFile:
    n = int(inputFile.readline().strip())
    nums = list(map(int, inputFile.readline().strip().split()))
    flag = True
    for i in range((n - 1) // 2):
        if nums[i] > nums[2 * i + 1] or nums[i] > nums[2 * i + 2]:
            flag = False
    if not n & 1 and nums[n - 1] < nums[(n-1)//2]:
        flag = False
    outputFile.write("YES" if flag else "NO")

