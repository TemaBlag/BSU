.686
.MODEL FLAT, C
.data 
tmp dd 0
tmp1 dd 0
asd dd 0
min dd 0
tmp2 dd 1000
x1 dd 0
sum dd 0
.CODE
sumint PROC 
mov edx, [esp + 12]
mov ebx, [esp + 4]
mov ecx, [esp + 8]
mov esi, ecx
mov eax, 0
mov edi, 0
cmp edx, 1
je beg1
beg:
  cmp edi, [ebx]
  je m2;
  add eax, [ebx]
  add ebx, 4
  inc tmp1
  jmp beg
beg1:
  cmp esi, 0
  je m1;
  add eax, [ebx]
  add ebx, 4
  dec esi
  jmp beg1
m1:
mov tmp, eax
finit
fild tmp
mov tmp, ecx
fild tmp
fdiv 
fst tmp
finit
jmp end_;
m2:
mov tmp, eax
finit
fild tmp
fild tmp1
fdiv 
fst tmp
finit
end_:
sub ebx, 4
finit
mov edx, [ebx]
cmp edx, 0
jl m3
mov x1, edx
fld tmp
fild x1
fsub
fabs
fst asd
finit
fild tmp2
fld asd
fcomi st[0], st[1]
ja end_
fist tmp2
mov min, edx
jmp end_
finit 
m3:
mov eax, min
ret
sumint ENDP
END
