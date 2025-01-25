#include <iostream>
#include <string>
#include <algorithm>
#include <numeric>
#include <vector>

long long nums[700001];

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);
    freopen("C:\\Users\\Artem\\Desktop\\input.txt", "r", stdin);
    freopen("C:\\Users\\Artem\\Desktop\\output.txt", "w", stdout);
    int n;
    std::cin >> n;
    for (int i = 0; i < n; i++) {
        std::cin >> nums[i];
    }
    std::vector<int> values;
    for (int i = 0; i < n; i++) {
        long long x = nums[i];
        long k = 0;
        std::vector<int> valK;;
        for (int j = 0; j < i; j++){
            if (nums[j] < x){
                if (k < values[j]) {
                    k = values[j];
                    valK.push_back(k + 1);
                    continue;
                }
            }
            if (std::find(valK.begin(), valK.end(), values[j]) == std::end(valK))
            nums[j] = 1000000001;
        }
        values.push_back(k + 1);
    }
    std::cout << *std::max_element(values.begin(), values.end());
    return 0;
}
