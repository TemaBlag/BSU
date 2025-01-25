#include <iostream>
#include <vector>

int main() {
   /* float a = 1;
    double b = 10;
    float dx1 = 0.009;
    double tmp = 1000;
    float a1 = 0;
    double res = 0;
    double res1 = 0;
    double sum = 0;
    double r = 1.5;
    double r1 = 3;
    //func x*x*x/3
   _asm
    {
   mbeg1:
       finit;
       fld a;
       fld dx1;
       fadd st(0) , st(1);
       fst a1;
       ////
       fld a;
       fld a;
       fmul;
       fst res1;
       finit;
       fld a;
       fld res1;
       fmul;
       fst a;
       finit;
       fld a;
       fld r;
       fdiv;
       fst res1;
       finit;
       //////
       fld a1;
       fld a1;
       fmul;
       fst res;
       finit;
       fld a1;
       fld res;
       fmul;
       fst a1;
       finit;
       fld a1;
       fld r;
       fdiv;
       fst res;
       finit;
       //////
       fld res;
       fld res1;
       fadd;
       fst sum;
       finit;
       fld a;
       fld b;
       fcomi st(0), st(1);
       je end_;
       finit;
       fld a;
       fld dx1;
       fadd;
       fst a;
       finit;
       jmp mbeg1;
   end_:
      /* fld a;
       fld a;
       fmul;
       fst res1;
       finit;
       fld a;
       fld res1;
       fmul;
       fst a;
       finit;
       fld a;
       fld r1;
       fdiv;
       fst res1;
       finit;
       /////
       fld b;
       fld b;
       fmul;
       fst res;
       finit;
       fld b;
       fld res;
       fmul;
       fst b;
       finit;
       fld b;
       fld r1;
       fdiv;
       fst res;
       finit;
       fld sum;
       fld res;
       fsub;
       fld res1;
       fsub;
       fst sum;
   beg:*/
        double a, b, delta = 0.00001;
        std::cin >> a >> b;
        double x = a;
        double result = 0;
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
                fst result
                finit
                fld result
                fld b
                fadd
                fst result
                finit
                fld x
                fld a
                fmul
                fld result
                fadd
                fst result
                fabs
                fld zero
                fcomi st(0), st(1)
                ja exit_
                dec ecx
                finit
                fld x
                fld delta
                fadd
                fst x
                jmp beg
                exit_ :
        }
        std::cout << x;
    return 0;
}