package ex2;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LongestLineFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            Optional<String> longestLine = lines.stream()
                    .max(Comparator.comparingInt(String::length));

            if (longestLine.isPresent()) {
                System.out.println("Самая длинная строка: " + longestLine.get());
                System.out.println("Длина строки: " + longestLine.get().length());
            } else {
                System.out.println("Файл пуст.");
            }
        } catch (NoSuchFileException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}

