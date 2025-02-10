def search(num, array, low, high):
    """
    binary search num in array which returns list:
    zero element - 1 if element in numbers else 0
    first element - index value which bigger or equal than element
    """
    if low == high:
        return [1 if array[low] == num else 0, low if array[low] >= num else low + 1]
    middle = (low + high) // 2
    if num == array[middle]:
        return [1, middle]
    if num > array[middle]:
        return search(num, array, middle + 1, high)
    if num < array[middle]:
        return search(num, array, low, middle - 1)


def binarySearch(num, array):
    """
    function which return list:
    zero element - 1 if exist element in numbers else 0
    first element - index value which bigger or equal than element
    second element - index value which bigger element
    """
    result = search(num, array, 0, len(array) - 1)
    if result[1] == n:
        result.append(n)
    else:
        for index in range(result[1], n):
            if array[index] != num:
                result.append(index)
                break
        if len(result) == 2:
            result.append(n)
    return result


# input data
n = int(input())
numbers = [int(x) for x in input().split()]
k = int(input())
requests = [int(x) for x in input().split()]
# processing data
outputData = []
for x in range(k):
    print(*binarySearch(requests[x], numbers))

