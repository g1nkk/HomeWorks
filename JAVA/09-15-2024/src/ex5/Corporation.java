package ex5;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Corporation {
    private List<Employee> employees;

    public Corporation() {
        employees = new ArrayList<>();
    }

    // Загрузка сотрудников из файла
    public void loadFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Список сотрудников загружен из файла.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Начинаем с пустого списка сотрудников.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке файла: " + e.getMessage());
        }
    }

    // Сохранение сотрудников в файл
    public void saveToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(employees);
            System.out.println("Список сотрудников сохранен в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    // Добавление сотрудника
    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Сотрудник добавлен.");
    }

    // Редактирование сотрудника по фамилии
    public void editEmployee(String lastName, Employee updatedEmployee) {
        Optional<Employee> employeeOpt = employees.stream()
                .filter(e -> e.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setAge(updatedEmployee.getAge());
            employee.setPosition(updatedEmployee.getPosition());
            employee.setSalary(updatedEmployee.getSalary());
            System.out.println("Данные сотрудника обновлены.");
        } else {
            System.out.println("Сотрудник с фамилией " + lastName + " не найден.");
        }
    }

    // Удаление сотрудника по фамилии
    public void removeEmployee(String lastName) {
        boolean removed = employees.removeIf(e -> e.getLastName().equalsIgnoreCase(lastName));
        if (removed) {
            System.out.println("Сотрудник удален.");
        } else {
            System.out.println("Сотрудник с фамилией " + lastName + " не найден.");
        }
    }

    // Поиск сотрудников по фамилии
    public List<Employee> searchByLastName(String lastName) {
        return employees.stream()
                .filter(e -> e.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    // Поиск сотрудников по возрасту или начальной букве фамилии
    public List<Employee> searchByAgeOrLastNameInitial(int age, char initial) {
        return employees.stream()
                .filter(e -> e.getAge() == age ||
                        (e.getLastName().length() > 0 &&
                                Character.toLowerCase(e.getLastName().charAt(0)) == Character.toLowerCase(initial)))
                .collect(Collectors.toList());
    }

    // Получение всех сотрудников
    public List<Employee> getAllEmployees() {
        return employees;
    }
}

