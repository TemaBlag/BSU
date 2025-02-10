n = int(input())
nums = list(map(int, input().split(" ")))
m = int(input())
reqs = list(map(int, input().split(" ")))
res = []
for i in reqs:
    start, finish = 0, n - 1
    index = (start + finish) // 2
    if nums[-1] < i:
        res.append((0, n, n))
        continue
    while start != finish:
        if nums[index] >= i:
            finish = index
        else:
            start = index + 1
        index = (start + finish) // 2
    start, finish = index, n - 1
    new_index = (start + finish) // 2
    new_i = i + 1
    if nums[-1] < new_i:
        res.append((int(nums[index] == i), index, n))
        continue
    while start != finish:
        if nums[new_index] >= new_i:
            finish = new_index
        else:
            start = new_index + 1
        new_index = (start + finish) // 2
    res.append((int(nums[index] == i), index, new_index))
for val in res:
    print(*val)











