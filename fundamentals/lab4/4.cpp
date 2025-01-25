#include <iostream>

int lastNumOfFactorial(long n) {
	long result = 1,p=10000000000;
	for (long x = 2; x <= n; x++) {
		result *= x;
		while (!(result % 10)) {
			result /= 10;
		}
		result %= p;
	}
	return result % 10;
};

int main()
{
	std::cout << "Input n: ";
	int n;
	std::cin >> n;
	std::cout << lastNumOfFactorial(n);
	return 0;
}