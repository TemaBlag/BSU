#include <iostream>
using namespace std;

int main()
{
	int rem, rem1;
	int num1 = 0b100000;
	int num10 = 0b1010;
	rem = num1 / num10;
	rem1 = num1 % num10;
	cout << rem << rem1;
	return 0;
}
