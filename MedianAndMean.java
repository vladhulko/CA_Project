import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Масив для зберігання введених чисел
        List<Integer> numbers = new ArrayList<>();

        // Флаг, що позначає, чи було натиснуто Enter в попередньому введенні
        boolean previousEnter = false;

        while (true) {
            // Зчитування введеного рядка
            String input = scanner.nextLine().trim();

            // Перевірка, чи введено порожній рядок (Enter)
            if (input.isEmpty()) {
                if (previousEnter) {
                    // Якщо попередній рядок також був порожнім (подвійний Enter),
                    // тоді виходимо з циклу
                    break;
                } else {
                    // Якщо це перший порожній рядок, то встановлюємо прапорець,
                    // що позначає, що був натиснутий Enter
                    previousEnter = true;
                    continue;
                }
            }

            // Розділення введеного рядка на числа за пробілами
            String[] numStrings = input.split("\\s+");

            // Парсимо кожне число та додаємо його до списку, ігноруючи некоректні значення
            for (String numString : numStrings) {
                try {
                    int number = Integer.parseInt(numString);
                    numbers.add(number);
                    previousEnter = false; // Очищуємо флаг, оскільки введено щось інше, а не порожній рядок
                } catch (NumberFormatException e) {
                    // Ігноруємо некоректне значення і продовжуємо зчитування
                    System.out.println("Ігноруємо некоректне число: " + numString);
                }
            }
        }

        // Перевірка, чи список не порожній перед обробкою
        if (!numbers.isEmpty()) {
            // Виклик методів обробки для обчислення медіани, середнього значення та сортування
            bubbleSort(numbers);

            // Виведення відсортованих чисел
            System.out.println("Відсортовані числа:");
            for (int number : numbers) {
                System.out.println(number);
            }

            // Обчислення та виведення медіани
            double median = calculateMedian(numbers);
            System.out.println("Медіана: " + median);

            // Обчислення та виведення середнього значення
            double average = calculateAverage(numbers);
            System.out.println("Середнє значення: " + average);
        } else {
            System.out.println("Список чисел порожній. Немає даних для обробки.");
        }
    }

    // Алгоритм сортування Bubble Sort
    private static void bubbleSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    // Метод для обчислення медіани
    private static double calculateMedian(List<Integer> arr) {
        int size = arr.size();
        if (size % 2 == 0) {
            return (arr.get(size / 2) + arr.get(size / 2 - 1)) / 2.0;
        } else {
            return arr.get(size / 2);
        }
    }

    // Метод для обчислення середнього значення
    private static double calculateAverage(List<Integer> arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.size();
    }
}
