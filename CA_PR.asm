;-----------------------------------------------------------------------
; Опис: Програма для зчитування десяткових чисел, конвертації їх в
; бінарне представлення, сортування, обчислення медіани та
; середнього значення.
;
; Вхід: Десяткові числа з консолі.
;
; Вихід: Медіана та середнє значення десяткових чисел у
; десятковому форматі.
;-----------------------------------------------------------------------

MODEL SMALL
STACK 100H

;-----------------------------------------------------------------------
; Дані
;-----------------------------------------------------------------------

buffer DB 255 DUP(?) ; Буфер для зчитування даних
num_array DW 10000 DUP(?) ; Масив для зберігання чисел
median DW ? ; Змінна для зберігання медіани
average DD 0 ; Змінна для зберігання середнього значення
num_count DW 0 ; Кількість прочитаних чисел

;-----------------------------------------------------------------------
; Процедури
;-----------------------------------------------------------------------

main PROC
    mov AX, @DATA
    mov DS, AX

    ; Зчитування даних
    call read_data

    ; Конвертація в бінарне представлення
    call dec_to_bin

    ; Сортування
    call bubble_sort

    ; Обчислення медіани
    call calc_median

    ; Виведення медіани
    mov AH, 9
    lea DX, median
    int 21h

    ; Обчислення середнього
    call calc_average

    ; Виведення середнього
    mov AH, 9
    lea DX, average
    int 21h

    ; Завершення програми
    mov AX, 4C00H
    int 21h

;-----------------------------------------------------------------------
; Зчитування даних
;-----------------------------------------------------------------------

read_data PROC
    mov DX, offset buffer
    mov AH, 1
    int 21h
    mov num_count, AX

    ; Обробка даних
    mov CX, num_count
    mov SI, offset buffer
    dec CX

read_loop:
    call read_decimal
    mov AX, num_array[CX]
    add AX, num_array[CX]
    mov num_array[CX], AX
    loop read_loop

    ret

;-----------------------------------------------------------------------
; Конвертація в бінарне представлення
;-----------------------------------------------------------------------

dec_to_bin PROC
    mov CX, num_count
    mov SI, offset num_array
    dec CX

dec_loop:
    mov AX, num_array[CX]
    call dec_to_bin_sub
    mov num_array[CX], AX
    loop dec_loop

    ret

;-----------------------------------------------------------------------
; Сортування алгоритмом Bubble Sort
;-----------------------------------------------------------------------

bubble_sort PROC
    mov CX, num_count
    dec CX

bubble_loop:
    push CX
    mov SI, offset num_array
    mov CX, num_count
    dec CX

inner_loop:
    mov AX, num_array[SI]
    cmp AX, num_array[SI+2]
    jl next_step
    xchg num_array[SI+2], AX
    mov num_array[SI], AX
next_step:
    add SI, 2
    loop inner_loop
    pop CX
    loop bubble_loop

    ret

;-----------------------------------------------------------------------
; Обчислення медіани
;-----------------------------------------------------------------------

calc_median PROC
    mov CX, num_count
    shr CX, 1
    mov BX, CX
    mov AX, num_array[CX]
    mov median, AX

    ; Якщо непарна кількість чисел
    test CX, 1
    jnz odd_median

    mov AX, num_array[BX]
    add AX, median
    shr AX, 1
    mov median, AX

    ret

odd_median:
    ret

;-----------------------------------------------------------------------
; Обчислення середнього значення
;-----------------------------------------------------------------------

calc_average PROC
    mov CX, num_count
    xor DX, DX
    mov AX, 0

