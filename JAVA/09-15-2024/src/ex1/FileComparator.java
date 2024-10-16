package ex1;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class FileComparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к первому файлу: ");
        String firstFilePath = scanner.nextLine();

        System.out.print("Введите путь ко второму файлу: ");
        String secondFilePath = scanner.nextLine();

        try {
            List<String> firstFileLines = Files.readAllLines(Paths.get(firstFilePath));
            List<String> secondFileLines = Files.readAllLines(Paths.get(secondFilePath));

            int maxLines = Math.max(firstFileLines.size(), secondFileLines.size());

            boolean filesMatch = true;

            for (int i = 0; i < maxLines; i++) {
                String line1 = i < firstFileLines.size() ? firstFileLines.get(i) : "";
                String line2 = i < secondFileLines.size() ? secondFileLines.get(i) : "";

                if (!line1.equals(line2)) {
                    filesMatch = false;
                    System.out.println("Несовпадающие строки на линии " + (i + 1) + ":");
                    System.out.println("Файл 1: " + line1);
                    System.out.println("Файл 2: " + line2);
                }
            }

            if (filesMatch) {
                System.out.println("Все строки в файлах совпадают.");
            }
        } catch (NoSuchFileException e) {
            System.err.println("Один из файлов не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файлов: " + e.getMessage());
        }
    }
}

