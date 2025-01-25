#include <iostream>

int main() {
        /*
         ///Integral x*x//
         double a, b;
         std::cout << "input a:";
         std::cin >> a;
         std::cout << "input b:";
         std::cin >> b;
         double res = 0, res1 = 0;
         double r = 3;
         //func integral x*x
         _asm
         {
             finit;
             fld a;
             fld a;
             fmul;
             fst res;
             finit;
             fld a;
             fld res;
             fmul;
             fst a;
             finit;
             fld a;
             fld r;
             fdiv;
             fst res;
             finit;
             fld b;
             fld b;
             fmul;
             fst res1;
             finit;
             fld b;
             fld res1;
             fmul;
             fst b;
             finit;
             fld b;
             fld r;
             fdiv;
             fld res;
             fsub;
             fst res;
             finit;
         }
         std::cout << res;
         //////Integral lnx/////
         int count = 1000;
         double a = 5, b = 16;
         double ch = 0, zn = 0;
         double e = 2.718281828459, one = 1;
         double curx, res = 0;
         double raz = 1000;
         _asm {
            finit
            fld   dword ptr one
            fld   dword ptr a
            fyl2x
            fstp ch
            fld dword ptr one
            fld dword ptr e
            fyl2x
            fstp zn
            fld ch
            fld zn
            fdiv
            fadd res
            fstp res
            fld   dword ptr one
            fld   dword ptr b
            fyl2x
            fstp ch
            fld dword ptr one
            fld dword ptr e
            fstp zn
            fld ch
            fld zn
            fdiv
            fadd res
            fstp res
            fld one
            fld one
            fadd
            fstp one
            fld res
            fld one
            fdiv
            fstp res
            fld b
            fld a
            fsub
            fdiv raz
            fst raz
            fld raz
            fld a
            fadd
            fstp curx
         beg:
            fld   dword ptr one
            fld   dword ptr curx
            fyl2x
            fstp ch
            fld dword ptr one
            fld dword ptr e
            fyl2x
            fstp zn
            fld ch
            fld zn
            fdiv
            fadd res
            fstp re
            fld raz
            fld curx
            fadd
            fstp curx
            dec count
            mov eax, count
            cmp eax, 0
            jge beg
            fld res
            fld raz
            fmul
            fstp res
         }
         cout << res <<" " << one <<  endl;
         //////a*x*x*x+b*x=0////
         double a, b;
         std::cout << "input a:";
         std::cin >> a;
         std::cout << "input b:";
         std::cin >> b;
         double dx1 = 0.00001;
         double x = a;
         double res = 0;
         double zero = 0.0001;
         double steps = (b - a) / 0.00001;
         int stps = steps;
         _asm {
            mov ecx, stps
            beg :
           cmp ecx, 0
            fld x
            fld x
            fmul
            fld x
            fmul
            fst res
            finit
            fld res
            fld b
            fadd
            fst res
            finit
            fld x
            fld a
            fmul
            fld res
            fadd
            fst res
            fabs
            fld zero
            fcomi st(0), st(1)
            ja end_
            dec ecx
            finit
            fld x
            fld dx1
            fadd
            fst x
            jmp beg
            end_ :
    }
         std::cout << x;
        */
        return 0;
}