
def merge(start, finish):
    lenFinish = len(finish)
    lenStart = len(start)
    res = [0] * (lenStart + lenFinish)
    indexStart = 0
    indexFinish = 0
    for index in range(lenStart + lenFinish):
        if lenFinish <= indexFinish or (lenStart - indexStart and
                                        start[indexStart] < finish[indexFinish]):
            res[index] = start[indexStart]
            indexStart += 1
        else:
            res[index] = finish[indexFinish]
            indexFinish += 1
    return res


def mergeSort(nums, start, finish):
    if start < finish - 1:
        middle = (start + finish) // 2
        leftPart = mergeSort(nums, start, middle)
        rightPart = mergeSort(nums, middle + 1, finish)
        return merge(leftPart, rightPart)
    elif start == finish - 1:
        if nums[start] > nums[finish]:
            nums[start], nums[finish] = nums[finish], nums[start]
    return nums[start:finish + 1]


n = int(input())
nums = [int(x) for x in input().split()]
print(*mergeSort(nums, 0, n - 1))
