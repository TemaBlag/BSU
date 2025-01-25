.686
.MODEL FLAT, C
.CODE
some  PROC
 MOV eax,-10
 MOV edx,0
 MOV ebx,-2
 MOV ecx, 1
 IMUL ecx
 IDIV ebx
 RET
 some ENDP
 END