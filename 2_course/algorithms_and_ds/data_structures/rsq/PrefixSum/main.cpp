#include <iostream>
#include <string>
long long prefix_sum[300003];
int main() {
    int n;
    std::cin >> n;
    for (int i = 0; i < n; i++) {
        long long val;
        std::cin >> val;
        prefix_sum[i + 1] = prefix_sum[i] + val;
    }
    int q;
    std::cin >> q;
    int index = 0;
    std::string nums;
    for (int i = 0; i < q; i++){
        std::string val;
        long long val1, val2;
        std::cin >> val >> val1 >> val2;
        if (val == "FindSum") {
            long long ans = prefix_sum[val2] - prefix_sum[val1];
            std::cout << ans << std::endl;
        }
        else {
            for (long long j = val1 + 1; j <= n; j++)
                prefix_sum[j] += val2;
        }
    }
    return 0;
}