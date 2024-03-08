import java.util.Scanner;

public class pr {
    private static void parseShort(String s)
        try{
            return Short.parseShort(s);
        } catch (NumberFormatException e) {
            return (short) (Integer.parseInt(s) ^ 0x8000);
        }

    }
    private static void mergeSort(short[] numbers, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(numbers, left, middle);
            mergeSort(numbers, middle + 1, right);
            merge(numbers, left, middle, right);
        }
    }
    private static void merge (short[], int left, int middle, int right) {
        short[] leftArray = new short[middle - left + 1];
        short[] rightArray = new short[right - middle];

        System.arraycopy(numbers, left, leftArray, 0, leftArray.length);
        System.arraycopy(numbers, middle + 1, rightArray, 0, rightArray.length);

        int i = 0, j = 0, k = left;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                numbers[k++] = leftArray[i++];
            } else {
                numbers[k++] = rightArray[j++];
            }
        }
        while (i < leftArray.length) {
            numbers[k++] = leftArray[i++];
        }
        while (j < rightArray.length) {
            numbers[k++] = rightArray[j++];
        }
    }
}