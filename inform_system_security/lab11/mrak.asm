.Model SMALL
.data
OblVvoda db 15,?,15 dup (?)
.code
UU:
 mov  ax,@data
 mov ds,ax

 mov byte ptr cs:[0028h], 05Ah
 mov byte ptr cs:[002Dh], 042h
 mov byte ptr cs:[002Fh], 026h
 mov byte ptr cs:[0033h], 01Eh

 mov ah,10
 lea dx,OblVvoda
 int 21h
 mov OblVvoda+12,'è'
 mov OblVvoda+14,'è'

 mov bl, OblVvoda+13
 mov ah, OblVvoda+15

 mov OblVvoda+13, bl
 mov OblVvoda+15, ah

 mov  ah,9
 lea DX,OblVvoda+2
 mov OblVvoda+16,'$'
 int 21h
 mov ah,4ch
 int 21h
 end  UU