n = int(input())
X = [0] + list(map(int, input().split()))
Y = [0] + list(map(int, input().split()))
nums = [[0] * (n + 1) for i in range(n + 1)]
for i in range(1, n + 1):
    for j in range(1, n + 1):
        nums[i][j] = nums[i - 1][j - 1] + 1 if X[i] == Y[j] else max(nums[i - 1][j], nums[i][j - 1])
print(nums[n][n])
i, j = n, n
wayX = []
wayY = []
while i * j > 0:
    if X[i] == Y[j]:
        wayX.append(i - 1)
        wayY.append(j - 1)
        i -= 1
        j -= 1
    else:
        if nums[i - 1][j] == nums[i][j]:
            i -= 1
        else:
            j -= 1
print(wayX[::-1])
print(wayY[::-1])
