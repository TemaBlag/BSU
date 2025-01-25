PUBLIC meanvalue
EXTERN puts:PROC

.data

    sum qword 0
    count qword 0

.code

meanvalue PROC
    push rbp
    mov rbp, rsp
    sub rsp, 32           
    and spl, -16  
    mov count, rcx
    beg:
    cmp rcx, 0
    je end_
      mov r8 , [rdx] 
      add sum, r8
      add rdx, 8
      dec rcx
      jmp beg
    end_:  
   mov rax, sum
   leave
    ret
meanvalue ENDP

END
