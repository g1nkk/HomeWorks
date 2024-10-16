package ex3;

import java.util.*;
import java.util.stream.*;

public class DeviceDemo {
    // Перечисления для цвета и типа устройства
    public enum Color {
        BLACK, WHITE, RED, BLUE, GREEN, YELLOW
    }

    public enum DeviceType {
        PHONE, LAPTOP, TABLET, SMARTWATCH, DESKTOP, PRINTER
    }

    // Класс Device
    public static class Device {
        private String name;
        private int year;
        private double price;
        private Color color;
        private DeviceType type;

        public Device(String name, int year, double price, Color color, DeviceType type) {
            this.name = name;
            this.year = year;
            this.price = price;
            this.color = color;
            this.type = type;
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

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public DeviceType getType() {
            return type;
        }

        public void setType(DeviceType type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Device{name='" + name + "', year=" + year + ", price=" + price +
                    ", color=" + color + ", type=" + type + "}";
        }
    }

    public static void main(String[] args) {
        // Инициализация списка устройств
        List<Device> devices = Arrays.asList(
                new Device("iPhone 13", 2021, 999.99, Color.BLACK, DeviceType.PHONE),
                new Device("Galaxy Tab S7", 2020, 649.99, Color.WHITE, DeviceType.TABLET),
                new Device("Apple Watch Series 6", 2020, 399.99, Color.RED, DeviceType.SMARTWATCH),
                new Device("Dell XPS 13", 2021, 1199.99, Color.BLACK, DeviceType.LAPTOP),
                new Device("HP LaserJet Pro", 2019, 299.99, Color.BLUE, DeviceType.PRINTER),
                new Device("iPad Pro", 2021, 799.99, Color.GREEN, DeviceType.TABLET),
                new Device("Asus ROG Desktop", 2022, 1499.99, Color.BLACK, DeviceType.DESKTOP)
        );

        Scanner scanner = new Scanner(System.in);

        // Задача 1: Показать все устройства
        System.out.println("Все устройства:");
        devices.stream()
                .forEach(System.out::println);

        // Задача 2: Показать все устройства заданного цвета
        System.out.print("\nВведите цвет устройства (BLACK, WHITE, RED, BLUE, GREEN, YELLOW): ");
        String colorInput = scanner.nextLine().toUpperCase();
        try {
            Color selectedColor = Color.valueOf(colorInput);
            System.out.println("Устройства цвета " + selectedColor + ":");
            devices.stream()
                    .filter(d -> d.getColor() == selectedColor)
                    .forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный цвет.");
        }

        // Задача 3: Показать все устройства заданного года выпуска
        System.out.print("\nВведите год выпуска устройства: ");
        int yearInput = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        System.out.println("Устройства выпущенные в " + yearInput + " году:");
        devices.stream()
                .filter(d -> d.getYear() == yearInput)
                .forEach(System.out::println);

        // Задача 4: Показать все устройства дороже заданной цены
        System.out.print("\nВведите цену для фильтрации устройств дороже нее: ");
        double priceInput = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера
        System.out.println("Устройства дороже " + priceInput + ":");
        devices.stream()
                .filter(d -> d.getPrice() > priceInput)
                .forEach(System.out::println);

        // Задача 5: Показать все устройства заданного типа
        System.out.print("\nВведите тип устройства (PHONE, LAPTOP, TABLET, SMARTWATCH, DESKTOP, PRINTER): ");
        String typeInput = scanner.nextLine().toUpperCase();
        try {
            DeviceType selectedType = DeviceType.valueOf(typeInput);
            System.out.println("Устройства типа " + selectedType + ":");
            devices.stream()
                    .filter(d -> d.getType() == selectedType)
                    .forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный тип устройства.");
        }

        // Задача 6: Показать все устройства, чей год выпуска находится в указанном диапазоне
        System.out.print("\nВведите начальный год диапазона: ");
        int startYear = scanner.nextInt();
        System.out.print("Введите конечный год диапазона: ");
        int endYear = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        System.out.println("Устройства выпущенные между " + startYear + " и " + endYear + " годами:");
        devices.stream()
                .filter(d -> d.getYear() >= startYear && d.getYear() <= endYear)
                .forEach(System.out::println);
    }
}
