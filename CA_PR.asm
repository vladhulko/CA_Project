.MODEL SMALL
.STACK 100h

.DATA
    input_buffer DB 256 DUP (?)   ; Буфер для зберігання введених даних

.CODE
main PROC
    ; Код основної програми тут
    ; Виклик підпрограм вводу та виводу
    ; Підготовка та робота з даними
    ; Закінчення програми

    ; Виклик підпрограми для виводу рядка
    call print_buffer
    
    ; Виклик підпрограми для введення рядка
    call read_string

    ; Виклик підпрограми для виводу рядка
    call print_buffer

    ; Закінчення програми
    mov ah, 4Ch
    int 21h
main ENDP

; Підпрограма для виводу символів з буфера на екран
print_buffer PROC
    mov ah, 09h         ; Код сервісу для виводу рядка
    lea dx, input_buffer   ; Завантаження адреси буфера у DX
    int 21h             ; Виклик сервісу DOS
    ret
print_buffer ENDP

; Підпрограма для введення рядка з клавіатури
read_string PROC
    mov ah, 0Ah          ; Код сервісу для введення рядка
    lea dx, input_buffer    ; Завантаження адреси буфера у DX
    int 21h              ; Виклик сервісу DOS
    ret
read_string ENDP

END main
