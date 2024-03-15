**Hulko Vladyslav Variant 2**


1. Read from stdin N decimal numbers separated by a space or a newline before EOF (maximum line length 255 characters), the number of numbers can be up to 10000.
The strings are separated EITHER by a sequence of bytes 0x0D and 0x0A (CR LF) or by a single character - 0x0D or 0x0A.

2. Each number is a decimal integer that must be converted to a binary representation (word in the complementary code).
Negative numbers start with '-'.
Note: if the number is too large by absolute value for the 16-bit signed representation, the value should be represented as large as possible (by absolute value).

3. Sort binary values using the bubble sort (asc) algorithm. If you use merge sort, you will get an extra point.
Calculate the median value and print the decimal to the console as a string (stdout)
Calculate the average value and print the decimal to the console as a string (stdout)
