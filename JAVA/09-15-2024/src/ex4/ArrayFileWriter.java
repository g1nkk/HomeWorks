package ex4;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class ArrayFileWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к файлу для записи: ");
        String filePath = scanner.nextLine();

        System.out.print("Введите элементы массива через пробел: ");
        String arrayInput = scanner.nextLine();

        int[] array;
        try {
            array = Arrays.stream(arrayInput.trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: Введите только целые числа.");
            return;
        }

        // Исходный массив
        String originalArray = Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        // Четные числа
        String evenNumbers = Arrays.stream(array)
                .filter(n -> n % 2 == 0)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        // Нечетные числа
        String oddNumbers = Arrays.stream(array)
                .filter(n -> n % 2 != 0)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        // Перевернутый массив
        String reversedArray = IntStream.rangeClosed(1, array.length)
                .map(i -> array[array.length - i])
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        List<String> linesToWrite = Arrays.asList(
                originalArray,
                evenNumbers,
                oddNumbers,
                reversedArray
        );

        try {
            Files.write(Paths.get(filePath), linesToWrite, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}

