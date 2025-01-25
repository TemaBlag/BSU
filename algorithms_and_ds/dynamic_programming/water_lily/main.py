n = int(input())
nums = list(map(int, input().split(" ")))
res = [-1] * (n + 1)
way = [0] * (n + 1)
res[1] = nums[0]
if n > 2:
    res[3] = nums[2] + nums[0]
    way[3] = 1
if n > 3:
    res[4] = nums[3] + nums[0]
    way[4] = 1
if n > 4:
    res[5] = nums[4] + nums[2] + nums[0]
    way[5] = 3
if n > 5:
    for i in range(6, n + 1):
        index = i - 3 if res[i - 3] > res[i - 2] else i - 2
        res[i] = res[index] + nums[i - 1]
        way[i] = index
if n == 2:
    print(-1)
else:
    res_way = []
    print(res[n])
    while n > 0:
        res_way.append(n)
        n = way[n]
    print(*res_way[::-1])



