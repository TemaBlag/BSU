#include <iostream>
using namespace std;
 
int main() {
	int num, rem;
	cout << "nfl";
	cin >> num;
	while (num > 0) {
		rem = num % 2;
		num = num / 2;
		cout << rem;
	}
    return 0;
}