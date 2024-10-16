package ex2;

import java.util.*;
import java.util.stream.*;

public class ProductDemo {
    // Перечисление для категорий продуктов
    public enum Category {
        DAIRY,
        MEAT,
        VEGETABLES,
        FRUITS,
        BAKERY,
        BEVERAGES
    }

    // Класс Product
    public static class Product {
        private String name;
        private Category category;

        public Product(String name, Category category) {
            this.name = name;
            this.category = category;
        }

        // Геттеры и сеттеры
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        @Override
        public String toString() {
            return "Product{name='" + name + "', category=" + category + "}";
        }
    }

    public static void main(String[] args) {
        // Инициализация списка продуктов
        List<Product> products = Arrays.asList(
                new Product("Milk", Category.DAIRY),
                new Product("Cheese", Category.DAIRY),
                new Product("Bread", Category.BAKERY),
                new Product("Apple", Category.FRUITS),
                new Product("Orange", Category.FRUITS),
                new Product("Carrot", Category.VEGETABLES),
                new Product("Beer", Category.BEVERAGES),
                new Product("Sausage", Category.MEAT),
                new Product("Milk", Category.DAIRY),
                new Product("Juice", Category.BEVERAGES),
                new Product("Eggs", Category.DAIRY),
                new Product("Milk", Category.DAIRY)
        );

        // Задача 1: Показать все продукты
        System.out.println("Все продукты:");
        products.stream()
                .forEach(System.out::println);

        // Задача 2: Показать все продукты с названием меньше пяти символов
        System.out.println("\nПродукты с названием меньше пяти символов:");
        products.stream()
                .filter(p -> p.getName().length() < 5)
                .forEach(System.out::println);

        // Задача 3: Посчитать, сколько раз встречается продукт, название которого вводит пользователь
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведите название продукта для подсчета его количества: ");
        String userInput = scanner.nextLine();
        long count = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(userInput))
                .count();
        System.out.println("Продукт \"" + userInput + "\" встречается " + count + " раз(а).");

        // Задача 4: Показать все продукты, которые начинаются на заданную букву
        System.out.print("\nВведите букву для поиска продуктов, начинающихся на нее: ");
        String letterInput = scanner.nextLine();
        if (letterInput.length() != 1) {
            System.out.println("Введите только одну букву.");
        } else {
            char letter = Character.toLowerCase(letterInput.charAt(0));
            System.out.println("Продукты, начинающиеся на букву '" + letterInput + "':");
            products.stream()
                    .filter(p -> Character.toLowerCase(p.getName().charAt(0)) == letter)
                    .forEach(System.out::println);
        }

        // Задача 5: Показать все продукты из категории «Молоко» (DAIRY)
        System.out.println("\nПродукты из категории DAIRY:");
        products.stream()
                .filter(p -> p.getCategory() == Category.DAIRY)
                .forEach(System.out::println);
    }
}

