#include <iostream>
using namespace std;

void num(int k, int n, int count) {
    int h = n * (n + 1) / 2;
    if (h <= k ) {
        n++;
        count++;
        return num(k, n, count);
    }
    else {
        cout << count;
    }
}

int main() {
    int k;
    int n = 1;
    cin >> k;
    num(k, n, 0);
    return 0;
}