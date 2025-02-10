#include <iostream>
#include <string>

long long nums[300003];
long long segment_tree[1200012];

  void build_tree(long long index, long long left, long long right) {
      if (left == right)
          segment_tree[index] = nums[left];
      else {
          long long k = (left + right) >> 1;
          build_tree(index << 1, left, k);
          build_tree((index << 1) + 1, k + 1, right);
          segment_tree[index] = segment_tree[index << 1] + segment_tree[(index << 1) + 1];
      }
  }

long long find_sum(long long node, long long n_left, long long n_right, long long left, long long right) {
    if (left > right)
         return 0;
    if (n_left == left && n_right == right)
        return segment_tree[node];
    else {
        long long k = (n_left + n_right) >> 1;
        return (find_sum(node << 1, n_left, k, left, std::min(k, right)) +
                find_sum((node << 1) + 1, k + 1, n_right, std::max(k + 1, left), right));
    }
}

void add_node(long long node, long long n_left, long long n_right, long long index, long long value){
    segment_tree[node] += value;
    if (n_left == n_right && n_right == index)
        return;
    long long k = (n_left + n_right) >> 1;
    if (index > k)
        add_node((node << 1) + 1, k + 1, n_right, index, value);
    else
        add_node(node << 1, n_left, k, index, value);
}

int main() {
    int n, query;
    std::cin >> n;
    for (int i = 0; i < n; i++)
        std::cin >> nums[i];
    build_tree(1, 0, n - 1);
    std::cin >> query;
    for (int i = 0; i < query; i++) {
        std::string request;
        long long val1, val2;
        std::cin >> request >> val1 >> val2;
        if (request == "FindSum")
            std::cout << find_sum(1, 0, n - 1, val1, val2 - 1) << std::endl;
        else
            add_node(1, 0, n - 1, val1, val2);
    }
    return 0;
}
