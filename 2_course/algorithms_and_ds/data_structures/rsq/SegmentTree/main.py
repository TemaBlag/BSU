def build_tree(index, left, right):
    if left == right:
        segment_tree[index] = nums[left]
    else:
        k = (left + right) >> 1
        build_tree(index << 1, left, k)
        build_tree((index << 1) + 1, k + 1, right)
        segment_tree[index] = segment_tree[index << 1] + segment_tree[(index << 1) + 1]


def find_sum(node, n_left, n_right, left, right):
    if left > right:
        return 0
    if n_left == left and n_right == right:
        return segment_tree[node]
    else:
        k = (n_left + n_right) >> 1
        return (find_sum(node << 1, n_left, k, left, min(k, right)) +
                find_sum((node << 1) + 1, k + 1, n_right, max(k + 1, left), right))


def add_node(node, n_left, n_right, index, value):
    segment_tree[node] += value
    if n_left == n_right == index:
        return
    k = (n_left + n_right) >> 1
    if index > k:
        add_node((node << 1) + 1, k + 1, n_right, index, value)
    else:
        add_node(node << 1, n_left, k, index, value)


n = int(input())
nums = list(map(int, input().split()))
segment_tree = [0] * (4 * n)
build_tree(1, 0, n - 1)
query = int(input())
for i in range(query):
    request, val1, val2 = input().split()
    val1, val2 = int(val1), int(val2)
    if request == "FindSum":
        print(find_sum(1, 0, n - 1, val1, val2 - 1))
    else:
        add_node(1, 0, n - 1, val1, val2)
