#include <iostream>
#include <cmath>

int nums[300003];
long long sums[550];

long long findSum(int k, int left, int right){
    int leftBoard = left % k ? left / k + 1 : left / k;
    int rightBoard = right / k;
    long long answer = 0;
    for (int i = leftBoard; i < rightBoard; i++)
        answer += sums[i];
    for (int i = left; i < leftBoard * k; i++)
        answer += nums[i];
    for (int i = rightBoard * k; i < right; i++)
        answer += nums[i];
    return answer;
}

void add(int k, int index, int new_value){
    sums[index / k] += new_value;
    nums[index] += new_value;
}

int main() {
    int n, k, q;
    std::cin >> n;
    k = std::sqrt(n);
    for (int i = 0; i < n; i++)
        std::cin >> nums[i];
    sums[0] = nums[0];
    for (int i = 1; i < n; i++)
        sums[i / k] += nums[i];
    std::cin >> q;
    for (int i = 0; i < q; i++) {
        int val1, val2;
        std::string req;
        std::cin >> req >> val1 >> val2;
        if (req == "FindSum")
            std::cout << findSum(k, val1, val2) << std::endl;
        else
            add(k, val1, val2);
    }
    return 0;
}