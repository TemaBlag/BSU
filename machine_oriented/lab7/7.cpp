#include <iostream>
#include <vector>

extern "C" int some();

int main() {
    /*
    ///////////////Task1/////////////////////////////////////////////////
    int a;
     int result = 0;
     int b;
     int c = 0;
     std::cout << "Input number a: ";
     std::cin >> a;
     std::cout << "Input number b: ";
     std::cin >> b;
     if (a) {
         _asm
         {
             NEG b
             MOV eax, b
             MOV edx, 0
             CDQ
             IDIV a
             MOV result, eax
             MOV c, edx

         }
         if (!c) std::cout << "Divided entirely";
         else std::cout << "Not completely divided";

         std::cout <<std::endl <<  "x=" << result;
     }
     else if (!b && !a) {
         std::cout << "we have inf solutions";
     }
     else {
         std::cout << "we do not have solutions";
     }
     //////////////////Task2//////////////////////////////////////////////
     int x0 = 1;
     int x1 = 1;
     int x2 = 0;
     int count = 2;
     std::vector<int> a;
     a.push_back(1);
     while (x1 >= x0) {
         _asm {
             MOV eax,x1
             ADD eax,x0
             MOV x2, eax
             MOV ecx,x1
             MOV x0,ecx
             MOV ebx, x2
             MOV x1,ebx
             ADD count,1
         }
         a.push_back(x0);
     }
     for (auto i : a) {
         std::cout << i << " ";
     }
     std::cout << a.size();
     ////////////////Task3///////////////////////////////////////
     int y = -1;
     int x;
     int c;
     int res;
     for (int i = 0; i < 17; i++) {
         _asm
         {
             MOV eax,y
             ADD eax,1
             MOV y,eax
             MOV ebx,3
             IMUL ebx
             MOV ecx,2
             MOV edx,0
             IDIV ecx
             MOV c,edx
             MOV res,eax
         }
         x = 25 - res;
         if ((x >= 0) && (c == 0) ) {
             std::cout << x << " " << y << "|";
         }
     }
     ////////////Task4////////////////////////////////////
     int a;
     std::cout << "Input a=";
     std::cin >> a;
     int result, div;
     if (a) {
         _asm
         {
             MOV eax, a
             MOV ebx, a
             IMUL a
             MOV a, eax
             MOV eax, a
             IMUL a
             MOV a, eax
             MOV ecx, a
             IMUL a
             IMUL a
             MOV edx, 0
             ADD eax, ecx
             ADD eax, ebx
             IDIV ecx
             MOV div, edx
             MOV result, eax
         }
         if (!div) {
             std::cout << "Divided entirely, result:";
             std::cout << result;
         }
         else {
             std::cout << "Not completely divided, result:";
             std::cout << result;
         }
     }
     else std::cout << "Divison by zero";
     ////////////Task5////////////////////////////////////
     int a;
     int res = 1;
     int count = -1;
     std::cout << "Input a=";
     std::cin >> a;
     while (res > 0) {
         _asm {
             MOV eax,res
             IMUL a
             MOV res,eax
             ADD count,1
         }
     }
     std::cout << count;
     */
    return 0;