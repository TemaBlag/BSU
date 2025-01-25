.686
.model flat
.data
five dd 5
.code
_funcsumthree proc
mov eax, [esp+4]
mov ebx, [esp+4]
mov ecx, [esp+4]
add eax, ebx
add eax, ecx
ret
_funcsumthree endp
end