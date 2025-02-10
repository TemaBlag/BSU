#include <iostream>
#include <fstream>

std::ifstream inputFile("C:/Users/Artem/Desktop/input.txt");
std::ofstream outputFile("C:/Users/Artem/Desktop/output.txt");

long long segment_tree[1200012][4] = {0,LLONG_MAX, LLONG_MIN, 0};

void push(long long node, long long left, long long right, long long value){
    long long k = (right - left + 1) / 2;
    segment_tree[node << 1][0] += value * (right - left + 1 - k);
    segment_tree[node << 1][1] += value;
    segment_tree[node << 1][2] += value;
    segment_tree[node << 1][3] += value;
    segment_tree[(node << 1) + 1][0] += value * k;
    segment_tree[(node << 1) + 1][1] += value;
    segment_tree[(node << 1) + 1][2] += value;
    segment_tree[(node << 1) + 1][3] += value;
    segment_tree[node][3] = 0;
}

long long find_sum(long long node, long long n_left, long long n_right, long long left, long long right) {
    if (right < n_left || left > n_right )
        return 0;
    if (n_left < n_right && segment_tree[node][3] != 0)
        push(node, n_left, n_right, segment_tree[node][3]);
    if (n_left >= left && n_right <= right)
        return segment_tree[node][0];
    else {
        long long k = (n_left + n_right) >> 1;
        return find_sum(node << 1, n_left, k, left, right) +
                find_sum((node << 1) + 1, k + 1, n_right, left, right);
    }
}

long long find_min(long long node, long long n_left, long long n_right, long long left, long long right) {
    if (left > right)
        return LLONG_MAX;
    if (n_left == left && n_right == right)
        return segment_tree[node][1];
    else {
        long long k = (n_left + n_right) >> 1;
        return std::min(find_min(node << 1, n_left, k, left, std::min(k, right)),
                        find_min((node << 1) + 1, k + 1, n_right, std::max(k + 1, left), right)) + segment_tree[node][3];
    }
}

long long find_max(long long node, long long n_left, long long n_right, long long left, long long right) {
    if (left > right)
        return LLONG_MIN;
    if (n_left == left && n_right == right)
        return segment_tree[node][2];
    else {
        long long k = (n_left + n_right) >> 1;
        return std::max(find_max(node << 1, n_left, k, left, std::min(k, right)),
                        find_max((node << 1) + 1, k + 1, n_right, std::max(k + 1, left), right))  + segment_tree[node][3];
    }
}

void add_node(long long node, long long n_left, long long n_right, long long index, long long value){
    if (n_left < n_right && segment_tree[node][3] != 0)
        push(node, n_left, n_right, segment_tree[node][3]);
    if (n_left == n_right && n_right == index) {
        segment_tree[node][0] = value; //sum
        segment_tree[node][1] = value; //min
        segment_tree[node][2] = value; //max
    } else {
        long long k = (n_left + n_right) >> 1;
        if (index > k)
            add_node((node << 1) + 1, k + 1, n_right, index, value);
        else
            add_node(node << 1, n_left, k, index, value);
        segment_tree[node][0] = segment_tree[node << 1][0] + segment_tree[(node << 1) + 1][0];
        segment_tree[node][1] = std::min(segment_tree[node << 1][1], segment_tree[(node << 1) + 1][1]);
        segment_tree[node][2] = std::max(segment_tree[node << 1][2], segment_tree[(node << 1) + 1][2]);
    }
}


void add_value(long long node, long long n_left, long long n_right, long long left, long long right, long long value){
    if (left > n_right || right < n_left)
        return;
    if (n_left < n_right && segment_tree[node][3] != 0)
        push(node, n_left, n_right, segment_tree[node][3]);
    if (left <= n_left && n_right <= right) {
        segment_tree[node][3] += value;
        segment_tree[node][2] += value;
        segment_tree[node][1] += value;
        segment_tree[node][0] += value * (n_right - n_left + 1);
    } else {
        long long k = (n_left + n_right) >> 1;
        add_value(node << 1, n_left, k, left, right, value);
        add_value((node << 1) + 1, k + 1, n_right, left, right, value);
        segment_tree[node][0] = segment_tree[node << 1][0] + segment_tree[(node << 1) + 1][0];
        segment_tree[node][1] = std::min(segment_tree[node << 1][1], segment_tree[(node << 1) + 1][1]);
        segment_tree[node][2] = std::max(segment_tree[node << 1][2], segment_tree[(node << 1) + 1][2]);
    }
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    int n;
    inputFile >> n;
    long long request, val1, val2, val3;
    while (inputFile >>  request){
        switch (request) {
            case 1:
                inputFile >>  val1 >> val2;
                add_node(1, 0, n - 1, val1, val2);
                break;
            case 2:
                inputFile >>  val1 >> val2 >> val3;
                add_value(1, 0, n - 1, val1, val2, val3);
                break;
            case 3:
                inputFile >>  val1 >> val2;
                outputFile << find_sum(1, 0, n - 1, val1, val2) << std::endl;
                break;
            case 4:
                inputFile >>  val1 >> val2;
                outputFile << find_min(1, 0, n - 1, val1, val2) << std::endl;
                break;
            case 5:
                inputFile >> val1 >> val2;
                outputFile << find_max(1, 0, n - 1, val1, val2) << std::endl;
                break;
        }
    }
    return 0;
}