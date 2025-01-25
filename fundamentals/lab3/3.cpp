#include <iostream>

int lastNumOfFactorial(int n) {
	int ostatok = n % 10;
	int celaychast = n / 10;
	int flag = 0;
	int ffllaagg = 0;
	switch (celaychast % 4) {
	case 0:
		flag = 6;
		break;
	case 1:
		flag = 8;
		break;
	case 2:
		flag = 4;
		break;
	case 3:
		flag = 2;
		break;
	}
	switch (ostatok) {
	case 0:
		ffllaagg = 1;
		break;
	case 1:
		ffllaagg = 1;
		break;
	case 2:
		ffllaagg = 2;
		break;
	case 3:
		ffllaagg = 6;
		break;
	case 4:
		ffllaagg = 4;
		break;
	case 5:
		ffllaagg = 2;
		break;
	case 6:
		ffllaagg = 2;
		break;
	case 7:
		ffllaagg = 4;
		break;
	case 8:
		ffllaagg = 2;
		break;
	case 9:
		ffllaagg = 8;
		break;
	}
	return (flag * ffllaagg) % 10;
};

int main()
{
	std::cout << "Input n: ";
	int n;
	std::cin >> n;
	std::cout << lastNumOfFactorial(n);
	return 0;
}
