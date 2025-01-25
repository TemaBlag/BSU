def radixSort(n, nums, x, phase):
    digits = [0] * 11
    dct = {}
    for num in range(10):
        dct[num] = []
    for el in nums:
        digits[int(el[x]) + 1] += 1
        dct[int(el[x])].append(el)
    print(f"Phase {phase}")
    for buck in range(10):
        string = ", ".join(dct[buck]) if dct[buck] else "empty"
        print(f"Bucket {buck}: {string}")
    print("".join(["*"] * 10))
    res = [0] * n
    for index in range(n):
        num = int(nums[index][x])
        numIndex = sum(digits[0:num + 1])
        res[numIndex] = nums[index]
        digits[num] += 1
        digits[num + 1] -= 1
    return res


n = int(input())
phase = 1
nums = [input() for _ in range(n)]
print("Initial array:")
print(", ".join(nums))
print("".join(["*"] * 10))
lenNum = len(nums[0])
for x in range(lenNum - 1, -1, -1):
    nums = radixSort(n, nums, x, phase)
    phase += 1
print("Sorted array:")
print(", ".join(nums))
