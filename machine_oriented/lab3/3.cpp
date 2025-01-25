#include <iostream>
using namespace std;

int main()
{
    /*/////////TASK1////////////////////
     int num,res = 0,i = 1;
     std::cin >> num;
    _asm {
     beg:
       MOV eax, res
       MOV ebx, 10
       IMUL ebx
       MOV res,eax
       MOV eax, num
       MOV edx, 0
       CDQ
       IDIV ebx
       ADD res, edx
       MOV num,eax
       cmp num,0
       je end_
       jmp beg
       end_:
     };
     std::cout << res;
    /////////////////TASK2///////////////////////////
    int a = 36, b = 28, nod = 0;
    _asm {
        mov eax, a;
        mov ebx, b;
        mov edx, 0;
        CDQ
    beg_:
        idiv ebx;
        cmp edx, 0;
        je end_;
        mov eax, ebx;
        mov ebx, edx;
        jmp beg_;
    end_:
        mov nod, ebx;
    }
    std::cout << nod;
    ////////////////TASK3/////////////////////
     int a, b, c, x1 = 15, x2 = 15, D, count = 0, sb = 1, curcount;
     cout << "ax^2 + bx + c = 0" << endl;
     cout << "input a: ";
     cin >> a;
     cout << "input b: ";
     cin >> b;
     cout << "input c: ";
     cin >> c;
     _asm {
      mov eax, a
      cmp eax, 0
      jne next1
    
      mov eax, b
      cmp eax, 0
      jne next1_1
      jmp end_
    
      next1_1:
      mov eax, -1
      imul c
      mov c, eax
      mov eax, c
      cdq
      idiv b
      cmp edx, 0
      jne end_
      mov x1, eax
      jmp end_
    
      next1:
      mov eax, b
      imul b
      mov D, eax
      mov eax, -4
       imul a
       imul c
      add eax, D
      mov D, eax
      cmp D, 0
      jl end_
       cmp D, 0
       jg discriminant
       mov eax, b
       cdq
       mov ecx, 2
       idiv ecx
       mov x1, eax
       mov eax, x1
       cdq
       idiv a
       neg eax
       mov x1, eax
    
       mov x2, eax
       jmp end_
    
       discriminant:
       cmp D, 0
       jl end_
        jne nextstep
    
        mov eax, 2
        imul a
        mov a, eax
    
        mov eax, count
        cdq
        add eax, b
        idiv a
        neg eax
        cmp edx, 0
        jne nextx
    
        mov x2, eax
        
        nextx:
       mov eax, b
        neg eax
        mov b, eax
    
        mov eax, count
        add eax, b
        mov b, eax
        mov eax, b
        cdq
        idiv a
        cmp edx, 0
        jne end_
    
        mov x1, eax
    
        nextstep:
        mov eax, D
        sub eax, sb
        mov D, eax
        inc sb
        inc sb
        inc count
        jmp discriminant
    
      end_:
     }
     cout << x1 << " " << x2 << endl;
    ///////////////TASK4///////////////////////////
    int change(int a) {
     int st = 1;
     int res = a;
     int cur;
     while (a) {
      if (!(a % 2)) {
       cur = res % st;
       res /= st;
       st *= 10;
       res *= st;
       res += cur;
      }
      a /= 10;
      st *= 10;
     }
     cout << "result1 is: " << res << endl;
     return res;
    
    }
    int main() {
     int a, cur = 0, str = 1, res;
     cout << "Input a: ";
     cin >> a;
     cout << endl;
     res = a;
     res = change(a);
     _asm {
      beg:
      mov eax, a
      cdq
      mov ecx, 2
      div ecx
      cmp edx, 2
      jne lets
       mov eax, res
       cdq
       mov ecx, str
       div str
       mov cur, edx
       mov res, eax
       mov eax, 10
       mul str
       mov str, eax
       mov eax, res
       mul str
       add eax, cur
       mov res, eax
    
       lets :
      mov eax, a
      cdq 
       mov ecx, 10
       div ecx
       mov a, eax
       mov eax, 10
       mul str
       mov str, eax
     }
     cout << "result2 is: " << res << endl;
     cout << "a is: " << a << endl;
     return 0;
    }
    */
    return 0;
}
