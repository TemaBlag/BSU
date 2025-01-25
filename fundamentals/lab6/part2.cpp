#include <iostream>
using namespace std;

void natural(int& n)
{
    int counter = 0;
    for (int i = 2; i < 1000; i++) {
        for (int j = 2; j <= (i / j); j++) {
            if (!(i % j)) break;

            if (j > (i / j)) {
                cout << i << " - simple number\n";
                counter++;
        }
};

int main() {
    cout << "Input n: " << endl;
    int n;
    cin >> n;
    natural(n);
    return 0;
}
