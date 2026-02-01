extrn MessageBoxA: PROC
extrn ExitProcess:PROC
extrn CreateThread:PROC
extrn FindWindowA:PROC
extrn MoveWindow:PROC
extrn Sleep:PROC
extrn CloseHandle:PROC

.data
caption				db		'64-bit hello', 0
message 			db 		'Hello, World!', 0
HWINDOW  			dword 	?
SLEEP_DURATION 		dword 	1
CURRENT_X     		dword 	450
CURRENT_Y     		dword 	350
MSGBOX_WIDTH   		dword 	133
MSGBOX_HEIGHT  		dword 	140
SCREEN_WIDTH        dword   1250
SCREEN_HEIGHT       dword   600
hThread		        dword   0
RAND_SEED			dword	42

.code

Start proc
    sub rsp, 28h

    mov RCX, 0
    mov RDX, 0
    mov R8, Move
    mov R9, 0
    mov qword ptr [RSP+20h], 0
    mov qword ptr [RSP+28h], 0
    call CreateThread
    mov [hThread], eax

    mov rcx, 0
    lea rdx, message
    lea r8, caption
    mov r9d, 0
    call MessageBoxA

    mov ecx, [hThread]
    call CloseHandle

    mov rcx, 0
    call ExitProcess

    add rsp, 28h
Start endp

random proc
    mov eax, [RAND_SEED]
    imul eax, eax, 1103515245
    add eax, 12345
    mov [RAND_SEED], eax
    shr eax, 16
    and eax, 32767
    ret
random endp

random_range proc
    push rcx
    push rdx
    call random
    pop rdx
    pop rcx
    sub edx, ecx
    inc edx
    xor r8d, r8d
    mov r8d, edx
    xor edx, edx
    div r8d
    mov eax, edx
    add eax, ecx
    ret
random_range endp

Move proc
FIND:
    mov RCX, 0
    lea RDX, caption
    call FindWindowA
    cmp RAX, 0
    je FIND
    mov [HWINDOW], eax

MAIN_LOOP:
    mov ECX, [SLEEP_DURATION]
    call Sleep

    mov rcx, -8
    mov rdx, 8
    call random_range
    cmp eax, 0
    jne SKIP_ADJUST_X
    mov eax, 1
SKIP_ADJUST_X:
    add [CURRENT_X], eax

    mov rcx, -8
    mov rdx, 8
    call random_range
    cmp eax, 0
    jne SKIP_ADJUST_Y
    mov eax, 1
SKIP_ADJUST_Y:
    add [CURRENT_Y], eax

    mov eax, [CURRENT_X]
    cmp eax, 0
    jg CHECK_RIGHT_X

    mov [CURRENT_X], 1
    mov rcx, 1
    mov rdx, 6
    call random_range
    mov [CURRENT_X], eax
    jmp CHECK_Y_BOUNDS

CHECK_RIGHT_X:
    mov ebx, [SCREEN_WIDTH]
    sub ebx, [MSGBOX_WIDTH]
    cmp eax, ebx
    jl CHECK_Y_BOUNDS

    mov ebx, [SCREEN_WIDTH]
    sub ebx, [MSGBOX_WIDTH]
    mov [CURRENT_X], ebx
    mov rcx, -6
    mov rdx, -1
    call random_range
    add [CURRENT_X], eax

CHECK_Y_BOUNDS:
    mov eax, [CURRENT_Y]
    cmp eax, 0
    jg CHECK_BOTTOM_Y

    mov [CURRENT_Y], 1
    mov rcx, 1
    mov rdx, 6
    call random_range
    mov [CURRENT_Y], eax
    jmp MOVE_THE_WINDOW

CHECK_BOTTOM_Y:
    mov ebx, [SCREEN_HEIGHT]
    sub ebx, [MSGBOX_HEIGHT]
    cmp eax, ebx
    jl MOVE_THE_WINDOW

    mov ebx, [SCREEN_HEIGHT]
    sub ebx, [MSGBOX_HEIGHT]
    mov [CURRENT_Y], ebx
    mov rcx, -6
    mov rdx, -1
    call random_range
    add [CURRENT_Y], eax

MOVE_THE_WINDOW:
    mov rcx, 0
    mov rdx, 2
    call random_range
    cmp eax, 0
    jne NO_SLEEP_CHANGE
    mov rcx, 5
    mov rdx, 30
    call random_range
    mov [SLEEP_DURATION], eax

NO_SLEEP_CHANGE:
    mov ecx, [HWINDOW]
    mov edx, [CURRENT_X]
    mov r8d, [CURRENT_Y]
    mov r9d, [MSGBOX_WIDTH]
    mov eax, [MSGBOX_HEIGHT]
    mov [rsp+20h], eax
    mov dword ptr [rsp+28h], 1
    call MoveWindow

    test eax, eax
    jnz MAIN_LOOP
    ret

Move endp
End