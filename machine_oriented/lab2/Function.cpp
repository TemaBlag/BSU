#include <iostream>
extern "C" int _cdecl funcsumthree(int, int, int);

int main()
{
    std::cout << funcsumthree(1,2,3);
}
