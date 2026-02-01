.MODEL SMALL
.DATA
CONST16     DB  16
HEXSYM      DB  '0123456789ABCDEF'
PATTERN     DB  '-XX$'
CODSYM      DB  ?

.CODE
.STARTUP


    MOV     AX, 40h
    MOV     ES, AX

    MOV     CODSYM, 0
    MOV     CX, 16

PRTABLE:
    PUSH    CX
    MOV     CX, 16

POVT:
    PUSH CX

    MOV     BH, ES:[62h]
    MOV     BL, 0
    MOV     AH, 0Ah
    MOV     AL, CODSYM
    
    MOV     CX, 1
    INT     10h

    MOV     AH, 03
    INT     10h

    MOV     AH, 02
    ADD     DL, 1
    INT     10h
;
    MOV     AL, CODSYM
    MOV     AH, 0
    DIV     CONST16

    MOV     BX, OFFSET HEXSYM
    XLAT
    MOV     PATTERN + 1, AL

    MOV     AL, AH
    XLAT
    MOV     PATTERN + 2, AL

    MOV     AH, 9
    LEA     DX, PATTERN
    INT     21h

    MOV     AH, 02
    MOV     DL, ' '
    INT     21h

    POP CX
    ADD     CODSYM, 1
    LOOP    POVT

    POP     CX
    LOOP    PRTABLE
   
    MOV AH, 03h
    INT 10h
    MOV AH, 02h
    DEC DH
    INT 10h

    MOV     AH, 4Ch
    INT     21h

END