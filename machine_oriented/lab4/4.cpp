#include <iostream>

int main()
{   /*///////////////TASK1/////////////////
    int a[] = { 100,2,10,2,4,6 };
    int b[6];
    int count = -1;
    unsigned int c = 0;
    int length1 = 6;
    _asm
    {
        lea eax, a;
        lea ebx, b;
        mov ecx, length1;
        mov esi, 0;
    begin:
        mov edx, 0;
        cmp esi, length1;
        ja end_1;
    beg:
        cmp edx, ecx;
        jbe m2;
    m3: inc esi;
        jmp begin;
    m2:
        cmp esi, edx;
        jne m1;
        inc count;
        mov c, edx;
        mov edx, count;
        mov edi, [eax + esi * 4];
        mov[ebx + edx * 4], edi;
        mov edx, c;
    m1:
        mov edi, [ebx + edx * 4];
        cmp[eax + esi * 4], edi;
        je m3;
        inc edx;
        jmp beg;
    end_1:
    }
    count++;
    for (int i = 0; i < count; i++) {
        std::cout << b[i] << " ";
    }
    ///////////////TASK2/////////////////
    int a[] = { 1, 3, 6, 8 , 10, 15 };
    int b[] = {1, 3, 4, 5 , 7, 9};
    int c[10];
    int lengthAB = 10;
    _asm
    {
        mov edi, 0;
        mov esi, 0;
        mov ecx, 0;
        mov edx, lengthAB;
    beg:
        cmp ecx, edx;
        ja end_;
        mov eax, a[edi * 4];
        mov ebx, b[esi * 4];
        je m1;
        cmp eax, ebx;
        ja m1;
        mov c[ecx * 4], eax;
        inc edi;
        inc ecx;
        jmp beg;
    m1 :
        mov c[ecx * 4], ebx;
        inc esi;
        inc ecx;
        jmp beg;
    end_:

    }
    for (auto i : c) {
        std::cout << i << " ";
    }*/
    return 0;
}
