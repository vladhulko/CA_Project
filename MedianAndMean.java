import java.util.Scanner;

public class MedianAndMean {

    private static final int MAX_SIZE = 10000;
    private static final int MAX_LINE_LENGTH = 255;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        short[] numbers = new short[MAX_SIZE];
        int count = 0;

        while (count < MAX_SIZE && scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() > MAX_LINE_LENGTH) {
                System.out.println("Error: Line exceeds maximum length.");
                continue;
            }
            String[] tokens = line.split("[\\r\\n\\s]+"); // Розбиття рядка за пробілами, LF, CR
            for (String token : tokens) {
                if (token.isEmpty()) {
                    continue;
                }
                numbers[count++] = parseShort(token);
            }
        }

        mergeSort(numbers, 0, count - 1);

        int medianIndex = count / 2;
        int median = numbers[medianIndex];

        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += numbers[i];
        }
        int mean = sum / count;

        System.out.println(median);
        System.out.println(mean);
    }

    private static short parseShort(String s) {
        try {
            return Short.parseShort(s);
        } catch (NumberFormatException e) {
            // Обробка переповнення
            if (s.charAt(0) == '-') {
                return Short.MIN_VALUE;
            } else {
                return Short.MAX_VALUE;
            }
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

    private static void merge(short[] numbers, int left, int middle, int right) {
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
