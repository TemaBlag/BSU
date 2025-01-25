#include <iostream>

int main() {
    int n,m;
    std::cin >> n >> m;
    long long ch = 1;
    int sub = n - m;
    long x = 7 + 1e9;
    long long fac = 1;
    for (int i = 1; i <= m; i++) {
        ch = (ch * (sub + i)) % x;
        fac = (fac * i) % x;
    }
    long long zn = 1;
    long deg = x - 2;
    while (deg){
        if (deg & 1) zn = (zn * fac) % x;
        fac = (fac * fac) % x;
        deg >>= 1;
    }
    long long rest = (ch * zn) % x;
    std::cout << rest ;
}
