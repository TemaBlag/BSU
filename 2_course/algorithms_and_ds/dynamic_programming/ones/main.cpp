#include <iostream>
#include <cmath>
#include <chrono>

int main(){
    auto start = std::chrono::high_resolution_clock::now();
    int n, m;
    std::cin >> n >> m;
    long coefficients[m + 1];
    for (int i = 1; i <= m; i++)
        coefficients[i] = 0;
    coefficients[0] = 1;
    long x = 7 + pow(10, 9);
    for (int i = 1; i < n / 2 + 2; i++) {
        for (int j = std::min(i, m); j > 0; j--){
            coefficients[j] = (coefficients[j - 1] + coefficients[j]) % x;
        }
    }
    for (int i = n - n / 2 - 1; i > 0; i--) {
        for (int j = i; j > 0; j--) {
        if (j + m - i > 0)
        coefficients[j + m - i] = (coefficients[j - 1 + m - i] + coefficients[j + m - i]) % x;
        }
    }
    std::cout << coefficients[m];
    auto end = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> duration = end - start;
    std::cout << "Execution time: " << duration.count() << " seconds" << std::endl;
    return 0;
}

