#include <iostream>
extern "C" int _cdecl sumint(int* a, int len, bool temp);

int main()
{
    int a[6] = { 1,2,1000,150,10,10000 };
    int length = 6;
    bool temp = 1;
    std::cout << sumint(a, length, temp);
}
