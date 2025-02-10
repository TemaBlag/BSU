n = int(input())
nums = list(map(int, input().split()))
prefix_sum = [0] * (n+1)
for i in range(n):
    prefix_sum[i + 1] = prefix_sum[i] + nums[i]
q = int(input())
adds = [[0, 0] for i in range(q)]
index = 0
dct = {
    'FindSum': 1,
    'Add': 0
}
for i in range(q):
    query, val1, val2 = input().split()
    val1, val2 = int(val1), int(val2)
    if dct[query]:
        ans = prefix_sum[val2] - prefix_sum[val1]
        for j in range(index):
            if val1 <= adds[j][0] < val2:
                ans += adds[j][1]
        print(ans)
    else:
        adds[index][0], adds[index][1] = val1, val2
        index += 1

