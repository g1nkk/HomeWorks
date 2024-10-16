package ex3;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class ArrayProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к файлу с массивами: ");
        String filePath = scanner.nextLine();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            List<int[]> arrays = lines.stream()
                    .map(line -> Arrays.stream(line.trim().split("\\s+"))
                            .mapToInt(Integer::parseInt)
                            .toArray())
                    .collect(Collectors.toList());

            int overallMax = Integer.MIN_VALUE;
            int overallMin = Integer.MAX_VALUE;
            long totalSum = 0;

            for (int i = 0; i < arrays.size(); i++) {
                int[] array = arrays.get(i);
                System.out.println("Массив " + (i + 1) + ": " + Arrays.toString(array));

                OptionalInt maxOpt = Arrays.stream(array).max();
                OptionalInt minOpt = Arrays.stream(array).min();
                int sum = Arrays.stream(array).sum();

                if (maxOpt.isPresent() && minOpt.isPresent()) {
                    int max = maxOpt.getAsInt();
                    int min = minOpt.getAsInt();
                    totalSum += sum;

                    System.out.println("Максимум: " + max);
                    System.out.println("Минимум: " + min);
                    System.out.println("Сумма элементов: " + sum);
                    System.out.println();

                    if (max > overallMax) {
                        overallMax = max;
                    }
                    if (min < overallMin) {
                        overallMin = min;
                    }
                }
            }

            System.out.println("Максимум среди всех массивов: " + overallMax);
            System.out.println("Минимум среди всех массивов: " + overallMin);
            System.out.println("Общая сумма всех элементов: " + totalSum);

        } catch (NoSuchFileException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка преобразования числа: " + e.getMessage());
        }
    }
}

