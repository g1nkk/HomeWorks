package ex5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CorporationApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Corporation corporation = new Corporation();

        System.out.print("Введите путь к файлу с данными сотрудников: ");
        String filePath = scanner.nextLine();

        // Загрузка сотрудников из файла при старте программы
        corporation.loadFromFile(filePath);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Информационная система 'Корпорация' ---");
            System.out.println("1. Добавить сотрудника");
            System.out.println("2. Редактировать сотрудника");
            System.out.println("3. Удалить сотрудника");
            System.out.println("4. Поиск сотрудника по фамилии");
            System.out.println("5. Вывод сотрудников по возрасту или начальной букве фамилии");
            System.out.println("6. Сохранить найденную информацию в файл");
            System.out.println("7. Вывод всех сотрудников");
            System.out.println("8. Выход");
            System.out.print("Выберите опцию: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEmployeeMenu(scanner, corporation);
                    break;
                case "2":
                    editEmployeeMenu(scanner, corporation);
                    break;
                case "3":
                    removeEmployeeMenu(scanner, corporation);
                    break;
                case "4":
                    searchByLastNameMenu(scanner, corporation);
                    break;
                case "5":
                    searchByAgeOrInitialMenu(scanner, corporation);
                    break;
                case "6":
                    saveSearchResultsMenu(scanner, corporation);
                    break;
                case "7":
                    displayAllEmployees(corporation);
                    break;
                case "8":
                    exit = true;
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }

        // Сохранение списка сотрудников при выходе из программы
        corporation.saveToFile(filePath);
        System.out.println("Программа завершена.");
    }

    private static void addEmployeeMenu(Scanner scanner, Corporation corporation) {
        System.out.println("\n--- Добавление сотрудника ---");
        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine();

        System.out.print("Введите возраст: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите должность: ");
        String position = scanner.nextLine();

        System.out.print("Введите зарплату: ");
        double salary = Double.parseDouble(scanner.nextLine());

        Employee employee = new Employee(firstName, lastName, age, position, salary);
        corporation.addEmployee(employee);
    }

    private static void editEmployeeMenu(Scanner scanner, Corporation corporation) {
        System.out.println("\n--- Редактирование сотрудника ---");
        System.out.print("Введите фамилию сотрудника для редактирования: ");
        String lastName = scanner.nextLine();

        List<Employee> foundEmployees = corporation.searchByLastName(lastName);

        if (foundEmployees.isEmpty()) {
            System.out.println("Сотрудник с фамилией " + lastName + " не найден.");
            return;
        }

        System.out.println("Найденные сотрудники:");
        for (int i = 0; i < foundEmployees.size(); i++) {
            System.out.println((i + 1) + ". " + foundEmployees.get(i));
        }

        System.out.print("Выберите номер сотрудника для редактирования: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= foundEmployees.size()) {
            System.out.println("Некорректный номер.");
            return;
        }

        Employee updatedEmployee = getEmployeeDetails(scanner);
        corporation.editEmployee(lastName, updatedEmployee);
    }

    private static void removeEmployeeMenu(Scanner scanner, Corporation corporation) {
        System.out.println("\n--- Удаление сотрудника ---");
        System.out.print("Введите фамилию сотрудника для удаления: ");
        String lastName = scanner.nextLine();

        corporation.removeEmployee(lastName);
    }

    private static void searchByLastNameMenu(Scanner scanner, Corporation corporation) {
        System.out.println("\n--- Поиск сотрудника по фамилии ---");
        System.out.print("Введите фамилию для поиска: ");
        String lastName = scanner.nextLine();

        List<Employee> foundEmployees = corporation.searchByLastName(lastName);

        if (foundEmployees.isEmpty()) {
            System.out.println("Сотрудники с фамилией " + lastName + " не найдены.");
        } else {
            System.out.println("Найденные сотрудники:");
            foundEmployees.forEach(System.out::println);
        }
    }

    private static void searchByAgeOrInitialMenu(Scanner scanner, Corporation corporation) {
        System.out.println("\n--- Поиск сотрудников по возрасту или начальной букве фамилии ---");
        System.out.print("Введите возраст: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите начальную букву фамилии: ");
        String initialInput = scanner.nextLine();
        char initial = initialInput.length() > 0 ? initialInput.charAt(0) : ' ';

        List<Employee> foundEmployees = corporation.searchByAgeOrLastNameInitial(age, initial);

        if (foundEmployees.isEmpty()) {
            System.out.println("Сотрудники, соответствующие критериям, не найдены.");
        } else {
            System.out.println("Найденные сотрудники:");
            foundEmployees.forEach(System.out::println);
        }
    }

    private static void saveSearchResultsMenu(Scanner scanner, Corporation corporation) {
        System.out.println("\n--- Сохранение найденной информации в файл ---");
        System.out.print("Введите путь к файлу для сохранения результатов поиска: ");
        String saveFilePath = scanner.nextLine();

        System.out.println("Введите критерии поиска:");
        System.out.print("Возраст: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Начальная буква фамилии: ");
        String initialInput = scanner.nextLine();
        char initial = initialInput.length() > 0 ? initialInput.charAt(0) : ' ';

        List<Employee> foundEmployees = corporation.searchByAgeOrLastNameInitial(age, initial);

        if (foundEmployees.isEmpty()) {
            System.out.println("Сотрудники, соответствующие критериям, не найдены. Файл не создан.");
            return;
        }

        List<String> lines = foundEmployees.stream()
                .map(Employee::toString)
                .collect(Collectors.toList());

        try {
            Files.write(Paths.get(saveFilePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Результаты поиска сохранены в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static void displayAllEmployees(Corporation corporation) {
        System.out.println("\n--- Все сотрудники ---");
        List<Employee> allEmployees = corporation.getAllEmployees();
        if (allEmployees.isEmpty()) {
            System.out.println("Список сотрудников пуст.");
        } else {
            allEmployees.forEach(System.out::println);
        }
    }

    private static Employee getEmployeeDetails(Scanner scanner) {
        System.out.print("Введите новое имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Введите новую фамилию: ");
        String lastName = scanner.nextLine();

        System.out.print("Введите новый возраст: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите новую должность: ");
        String position = scanner.nextLine();

        System.out.print("Введите новую зарплату: ");
        double salary = Double.parseDouble(scanner.nextLine());

        return new Employee(firstName, lastName, age, position, salary);
    }
}

