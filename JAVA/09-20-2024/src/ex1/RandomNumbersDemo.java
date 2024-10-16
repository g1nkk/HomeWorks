package ex1;

import java.util.*;
import java.util.stream.*;

public class RandomNumbersDemo {
    public static void main(String[] args) {
        // Генерация списка случайных чисел
        List<Integer> numbers = generateRandomNumbers(20, -100, 100);
        System.out.println("Сгенерированные числа: " + numbers);

        // Задача 1: Количество положительных чисел
        long positiveCount = numbers.stream()
                .filter(n -> n > 0)
                .count();
        System.out.println("Количество положительных чисел: " + positiveCount);

        // Задача 2: Количество отрицательных чисел
        long negativeCount = numbers.stream()
                .filter(n -> n < 0)
                .count();
        System.out.println("Количество отрицательных чисел: " + negativeCount);

        // Задача 3: Количество двухзначных чисел
        long twoDigitCount = numbers.stream()
                .filter(n -> Math.abs(n) >= 10 && Math.abs(n) <= 99)
                .count();
        System.out.println("Количество двухзначных чисел: " + twoDigitCount);

        // Задача 4: Количество зеркальных (палиндромных) чисел
        long palindromeCount = numbers.stream()
                .filter(RandomNumbersDemo::isPalindrome)
                .count();
        System.out.println("Количество зеркальных чисел: " + palindromeCount);
    }

    /**
     * Генерирует список случайных целых чисел.
     *
     * @param count количество чисел
     * @param min минимальное значение (включительно)
     * @param max максимальное значение (включительно)
     * @return список случайных чисел
     */
    private static List<Integer> generateRandomNumbers(int count, int min, int max) {
        Random random = new Random();
        return random.ints(count, min, max + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Проверяет, является ли число палиндромом.
     *
     * @param number число для проверки
     * @return true, если число палиндром, иначе false
     */
    private static boolean isPalindrome(int number) {
        String str = Integer.toString(Math.abs(number));
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}
