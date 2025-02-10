import numpy as np


def findSum(k, sums, nums, left, right):
    leftBoard = left // k + 1 if left % k else left // k
    rightBoard = (right // k)
    answer = np.sum(sums[leftBoard: rightBoard])
    answer += np.sum(nums[left: leftBoard * k])
    answer += np.sum(nums[rightBoard * k: right])
    return answer

def add(k, sums, nums, index, new_value):
    sums[index // k] += new_value
    nums[index] += new_value


n = int(input())
nums = np.array(list(map(int, input().split())))
k = int(n ** 0.5)
sums = np.zeros(k + 2, dtype=int)
sums[0] = nums[0]
for i in np.arange(1, n):
    sums[i // k] += nums[i]
q = int(input())
for i in np.arange(q):
    req, val1, val2 = np.array(input().split())
    val1, val2 = int(val1), int(val2)
    if req == "FindSum":
        print(findSum(k, sums, nums, val1, val2))
    else:
        add(k, sums, nums, val1, val2)
