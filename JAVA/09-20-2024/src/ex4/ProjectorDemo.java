package ex4;

import java.util.*;
import java.util.stream.*;

public class ProjectorDemo {
    // Перечисление для производителей
    public enum Manufacturer {
        EPSON, BENQ, ASUS, VIEWSONIC, SAMSUNG, LG
    }

    // Класс Projector
    public static class Projector {
        private String name;
        private int year;
        private double price;
        private Manufacturer manufacturer;

        public Projector(String name, int year, double price, Manufacturer manufacturer) {
            this.name = name;
            this.year = year;
            this.price = price;
            this.manufacturer = manufacturer;
        }

        // Геттеры и сеттеры
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Manufacturer getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(Manufacturer manufacturer) {
            this.manufacturer = manufacturer;
        }

        @Override
        public String toString() {
            return "Projector{name='" + name + "', year=" + year +
                    ", price=" + price + ", manufacturer=" + manufacturer + "}";
        }
    }

    public static void main(String[] args) {
        // Инициализация списка проекторов
        List<Projector> projectors = Arrays.asList(
                new Projector("Epson Home Cinema 2150", 2021, 799.99, Manufacturer.EPSON),
                new Projector("BenQ HT2050A", 2020, 749.99, Manufacturer.BENQ),
                new Projector("Asus P3B", 2022, 499.99, Manufacturer.ASUS),
                new Projector("ViewSonic PX747-4K", 2021, 999.99, Manufacturer.VIEWSONIC),
                new Projector("Samsung LU8000", 2022, 1199.99, Manufacturer.SAMSUNG),
                new Projector("LG PF50KA", 2020, 399.99, Manufacturer.LG),
                new Projector("Epson PowerLite 2250U", 2021, 1099.99, Manufacturer.EPSON),
                new Projector("BenQ TK850", 2022, 1299.99, Manufacturer.BENQ)
        );

        Scanner scanner = new Scanner(System.in);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // Задача 1: Показать все проекторы
        System.out.println("Все проекторы:");
        projectors.stream()
                .forEach(System.out::println);

        // Задача 2: Показать все проекторы одного производителя
        System.out.print("\nВведите производителя для поиска проекторов (EPSON, BENQ, ASUS, VIEWSONIC, SAMSUNG, LG): ");
        String manufacturerInput = scanner.nextLine().toUpperCase();
        try {
            Manufacturer selectedManufacturer = Manufacturer.valueOf(manufacturerInput);
            System.out.println("Проекторы производителя " + selectedManufacturer + ":");
            projectors.stream()
                    .filter(p -> p.getManufacturer() == selectedManufacturer)
                    .forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный производитель.");
        }

        // Задача 3: Показать все проекторы текущего года
        System.out.println("\nПроекторы текущего года (" + currentYear + "):");
        projectors.stream()
                .filter(p -> p.getYear() == currentYear)
                .forEach(System.out::println);

        // Задача 4: Показать все проекторы дороже заданной цены
        System.out.print("\nВведите цену для фильтрации проекторов дороже нее: ");
        double priceInput = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера
        System.out.println("Проекторы дороже " + priceInput + ":");
        projectors.stream()
                .filter(p -> p.getPrice() > priceInput)
                .forEach(System.out::println);

        // Задача 5: Показать все проекторы, отсортированные по цене по возрастанию
        System.out.println("\nПроекторы, отсортированные по цене по возрастанию:");
        projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::getPrice))
                .forEach(System.out::println);

        // Задача 6: Показать все проекторы, отсортированные по цене по убыванию
        System.out.println("\nПроекторы, отсортированные по цене по убыванию:");
        projectors.stream()
                .sorted(Comparator.comparingDouble(Projector::getPrice).reversed())
                .forEach(System.out::println);

        // Задача 7: Показать все проекторы, отсортированные по году выпуска по возрастанию
        System.out.println("\nПроекторы, отсортированные по году выпуска по возрастанию:");
        projectors.stream()
                .sorted(Comparator.comparingInt(Projector::getYear))
                .forEach(System.out::println);

        // Задача 8: Показать все проекторы, отсортированные по году выпуска по убыванию
        System.out.println("\nПроекторы, отсортированные по году выпуска по убыванию:");
        projectors.stream()
                .sorted(Comparator.comparingInt(Projector::getYear).reversed())
                .forEach(System.out::println);
    }
}

