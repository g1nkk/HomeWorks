package strings;

import java.util.Arrays;
import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) {
        // 2. Создание строк разными способами

        // 1) Литерал
        String literal = "Привет";

        // 2) new String()
        String newString = new String("Мир");

        // 3) Массив символов
        char[] chars = {'J', 'a', 'v', 'a'};
        String fromChars = new String(chars);

        // 4) Из байтов
        byte[] bytes = {72, 101, 108, 108, 111}; // "Hello" в ASCII
        String fromBytes = new String(bytes);

        // 5) С помощью StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(" ");
        sb.append("World");
        String fromBuilder = sb.toString();

        // Вывод созданных строк
        System.out.println("Созданные строки:");
        System.out.println("Литерал: " + literal);
        System.out.println("new String(): " + newString);
        System.out.println("Массив символов: " + fromChars);
        System.out.println("Из байтов: " + fromBytes);
        System.out.println("StringBuilder: " + fromBuilder);

        // 3. Работа со строкой
        // 3.1 Создать строку “Апельсин,Яблоко,Гранат,Груша”
        String fruits = "Апельсин,Яблоко,Гранат,Груша";

        // 3.2 Использовать split для извлечения названий фруктов в массив
        String[] fruitArray = fruits.split(",");
        System.out.println("\nМассив фруктов: " + Arrays.toString(fruitArray));

        // 3.3 Найти самое длинное название фрукта и преобразовать в нижний регистр
        String longestFruit = "";
        for (String fruit : fruitArray) {
            if (fruit.length() > longestFruit.length()) {
                longestFruit = fruit;
            }
        }
        System.out.println("Самое длинное название фрукта (в нижнем регистре): " + longestFruit.toLowerCase());

        // 3.4 Создать сокращенный вариант названия фрукта (первые 3 символа)
        String shortFruit = longestFruit.substring(0, 3);
        System.out.println("Сокращенный вариант названия фрукта: " + shortFruit);

        // 3.5 Создать строку с пробелами: “   Я_новая_строка      ”
        String spacedString = "   Я_новая_строка      ";

        // 3.6 Убрать лишние пробелы в начале и конце
        String trimmed = spacedString.trim();

        // 3.7 Заменить все символы ‘_’ на пробелы
        String finalString = trimmed.replace('_', ' ');

        // 3.8 Вывести окончательный вариант
        System.out.println("Окончательный вариант строки: \"" + finalString + "\"");

        // 3.9 Создать Scanner для ввода текста
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите текст: ");
        String input = scanner.nextLine();

        // Вывод введенного текста
        System.out.println("Вы ввели: " + input);

        // 3.10 Проверка, начинается ли текст со слова “Запуск”
        if (input.startsWith("Запуск")) {
            System.out.println("Запускаем процесс");
        }

        // 3.11 Проверка, заканчивается ли текст на слово “завершен”
        if (input.endsWith("завершен")) {
            System.out.println("Процесс завершен");
        }

        // 3.12 Проверка, содержит ли текст слово “ошибка” (без учета регистра)
        if (input.toLowerCase().contains("ошибка")) {
            System.out.println("Произошла ошибка");
        }

        // 4. Работа с StringBuilder
        StringBuilder sbuilder = new StringBuilder();

        // 4.1 Добавить все ранее созданные строки
        sbuilder.append(literal).append("\n");
        sbuilder.append(newString).append("\n");
        sbuilder.append(fromChars).append("\n");
        sbuilder.append(fromBytes).append("\n");
        sbuilder.append(fromBuilder).append("\n");
        sbuilder.append(fruits).append("\n");
        sbuilder.append(longestFruit).append("\n");
        sbuilder.append(shortFruit).append("\n");
        sbuilder.append(finalString).append("\n");
        sbuilder.append(input).append("\n");

        // 4.2 Добавить перенос на новую строку после каждой 3-й строки
        String[] sbLines = sbuilder.toString().split("\n");
        StringBuilder formattedBuilder = new StringBuilder();
        for (int i = 0; i < sbLines.length; i++) {
            formattedBuilder.append(sbLines[i]).append("\n");
            if ((i + 1) % 3 == 0) {
                formattedBuilder.append("\n"); // Добавляем дополнительный перенос
            }
        }

        // 4.3 Развернуть содержимое задом наперёд
        formattedBuilder.reverse();

        // 4.4 Собрать StringBuilder в строку и вывести на экран
        String finalOutput = formattedBuilder.toString();
        System.out.println("\nStringBuilder содержимое (развернутое):");
        System.out.println(finalOutput);
    }
}
