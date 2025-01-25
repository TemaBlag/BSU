from random import randint
def partition(elements, start, finish, x):
    indexEqual = start
    indexGreater = start
    for indexNow in range(start, finish):
        if elements[indexNow] < x:
            buf = elements[indexNow]
            elements[indexNow] = elements[indexGreater]
            elements[indexGreater] = elements[indexEqual]
            elements[indexEqual] = buf
            indexEqual += 1
            indexGreater += 1
        elif elements[indexNow] == x:
            buf = elements[indexNow]
            elements[indexNow] = elements[indexGreater]
            elements[indexGreater] = buf
            indexGreater += 1
    return indexEqual, indexGreater


def quickSort(elements, start, finish):
    if start < finish:
        x = elements[randint(start, finish)]
        p, q = partition(elements, start, finish, x)
        quickSort(elements, start, p)
        quickSort(elements, q, finish)

n = int(input())
nums = [int(x) for x in input().split()]
quickSort(nums, 0, n)
print(*nums)
