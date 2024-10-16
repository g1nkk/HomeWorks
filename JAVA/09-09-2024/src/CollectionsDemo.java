import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        // Задание 1: Базовая работа с List

        // a. Создать ArrayList<String> (list1)
        List<String> list1 = new ArrayList<>();
        System.out.println("Задание 1.a: Создан list1");

        // b. Создать массив строк. Добавить в список все элементы массива (одним методом)
        String[] array = {"Apple", "Banana", "Cherry", "Date"};
        list1.addAll(Arrays.asList(array));
        System.out.println("Задание 1.b: Добавлены элементы массива в list1: " + list1);

        // c. Создать новый (list2) ArrayList<String>, инициализировав его предыдущим списком
        List<String> list2 = new ArrayList<>(list1);
        System.out.println("Задание 1.c: Создан list2 на основе list1: " + list2);

        // d. Создать список (list3) используя Arrays.asList(…)
        List<String> list3 = Arrays.asList("Elderberry", "Fig", "Grape", "Honeydew");
        System.out.println("Задание 1.d: Создан list3: " + list3);

        // e. Вставить list3 в середину list2
        int middleIndex = list2.size() / 2;
        list2.addAll(middleIndex, list3);
        System.out.println("Задание 1.e: Вставлены элементы list3 в середину list2: " + list2);

        // f. Отсортировать список по убыванию
        list2.sort(Collections.reverseOrder());
        System.out.println("Задание 1.f: list2 отсортирован по убыванию: " + list2);

        // g. *Удалить каждый второй элемент списка используя ListIterator
        ListIterator<String> iterator = list2.listIterator();
        int count = 1;
        while (iterator.hasNext()) {
            iterator.next();
            if (count % 2 == 0) {
                iterator.remove();
            }
            count++;
        }
        System.out.println("Задание 1.g: list2 после удаления каждого второго элемента: " + list2);

        // Задание 2: Базовая работа с Set

        // a. Создать HashSet<String> (set1)
        Set<String> set1 = new HashSet<>();
        System.out.println("\nЗадание 2.a: Создан set1");

        // b. Вставить в set 2 произвольные строки
        set1.add("Orange");
        set1.add("Pineapple");
        System.out.println("Задание 2.b: Добавлены строки в set1: " + set1);

        // c. Вставить в set все элементы из list1 и list2
        set1.addAll(list1);
        set1.addAll(list2);
        System.out.println("Задание 2.c: Добавлены элементы из list1 и list2 в set1: " + set1);

        // d. Вывести на экран значения set1
        System.out.println("Задание 2.d: Значения set1: " + set1);

        // e. Создать LinkedHashSet<String> (set2)
        Set<String> set2 = new LinkedHashSet<>();
        System.out.println("\nЗадание 2.e: Создан set2 (LinkedHashSet)");

        // f. Вставить в set2 все элементы из list2 и list3
        set2.addAll(list2);
        set2.addAll(list3);
        System.out.println("Задание 2.f: Добавлены элементы из list2 и list3 в set2: " + set2);

        // g. Вывести на экран значения set2
        System.out.println("Задание 2.g: Значения set2: " + set2);

        // Задание 3: Базовая работа с Map

        // a. Создать LinkedHashMap<Integer, String> (map1)
        Map<Integer, String> map1 = new LinkedHashMap<>();
        System.out.println("\nЗадание 3.a: Создан map1 (LinkedHashMap)");

        // b. Добавить в map значения всех месяцев года (номер месяца : название)
        String[] months = {
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };
        for (int i = 0; i < months.length; i++) {
            map1.put(i, months[i]);
        }
        System.out.println("Задание 3.b: Добавлены месяцы в map1: " + map1);

        // c. Вывести на экран первый и последний месяц года (0 и 11)
        System.out.println("Задание 3.c:");
        System.out.println("Первый месяц (0): " + map1.get(0));
        System.out.println("Последний месяц (11): " + map1.get(11));

        // d. Вставить на место 6-го месяца слово ‘ОТПУСК’, вывести на экран
        map1.put(5, "ОТПУСК"); // Индексация начинается с 0, 5-й индекс - 6-й месяц
        System.out.println("Задание 3.d: Обновлен 6-й месяц: " + map1.get(5));

        // e. Создать HashMap<Integer, String> (map2)
        Map<Integer, String> map2 = new HashMap<>();
        System.out.println("Задание 3.e: Создан map2 (HashMap)");

        // f. Вставить в map2 все значения map1
        map2.putAll(map1);
        System.out.println("Задание 3.f: Добавлены все элементы из map1 в map2: " + map2);

        // g. *Создать метод для вывода всех элементов map в консоль (EntrySet<Integer, String>)
        // Вывести на экран map1 и map2
        System.out.println("Задание 3.g: Вывод всех элементов map1:");
        printMap(map1);

        System.out.println("Задание 3.g: Вывод всех элементов map2:");
        printMap(map2);

        // h. *Создать map3 (ключ – имя студента (String), значение - контакты студента: моб.тел, почта, скайп (Set<String>))
        Map<String, Set<String>> map3 = new HashMap<>();
        System.out.println("\nЗадание 3.h: Создан map3 (Map<String, Set<String>>)");

        // Пример добавления студентов в map3
        Set<String> contacts1 = new HashSet<>(Arrays.asList("123-456-7890", "student1@example.com", "student1Skype"));
        Set<String> contacts2 = new HashSet<>(Arrays.asList("098-765-4321", "student2@example.com", "student2Skype"));

        map3.put("Ivanov Ivan", contacts1);
        map3.put("Petrov Petr", contacts2);

        System.out.println("Задание 3.h: Добавлены студенты в map3:");
        for (Map.Entry<String, Set<String>> entry : map3.entrySet()) {
            System.out.println("Студент: " + entry.getKey() + ", Контакты: " + entry.getValue());
        }

        // Задание 4: Работа с своим классом
        // a. Создать класс User с полями name, age и phone
        // (Реализовано в User.java)
        System.out.println("\nЗадание 4.a: Класс User создан с полями name, age и phone");

        // b. Создать userSet (HashSet<User>)
        Set<User> userSet = new HashSet<>();
        System.out.println("Задание 4.b: Создан userSet (HashSet<User>)");

        // c. Создать 2 экземпляра класса User с одинаковыми полями и 1 экземпляр отличающийся возрастом
        User user1 = new User("Alice", 25, "555-1234");
        User user2 = new User("Alice", 25, "555-1234"); // Идентичный user1
        User user3 = new User("Alice", 30, "555-1234"); // Отличается возрастом
        System.out.println("Задание 4.c: Созданы пользователи user1, user2 (идентичные) и user3 (отличается возрастом)");

        // d. Добавить пользователей в userSet
        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);
        System.out.println("Задание 4.d: Пользователи добавлены в userSet");

        // e. Вывести пользователей на экран
        System.out.println("Задание 4.e: Содержимое userSet:");
        for (User user : userSet) {
            System.out.println(user);
        }

        // f. Переопределить в классе User метод hashCode, указав в нем только обработку поля name
        // (Переопределено в User.java)
        System.out.println("\nЗадание 4.f: Переопределен hashCode с учетом только поля name");

        // Очистка и повторное добавление пользователей
        userSet.clear();
        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);
        System.out.println("Пользователи добавлены заново после переопределения hashCode:");

        for (User user : userSet) {
            System.out.println(user);
        }

        // g. Вывести пользователей на экран после переопределения hashCode
        System.out.println("Задание 4.g: Содержимое userSet после переопределения hashCode:");
        for (User user : userSet) {
            System.out.println(user);
        }

        // h. Переопределить в классе User метод hashCode, указав в нем все поля класса
        // (Переопределено в User.java)
        System.out.println("\nЗадание 4.h: Переопределен hashCode с учетом всех полей");

        // Очистка и повторное добавление пользователей
        userSet.clear();
        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);
        System.out.println("Пользователи добавлены заново после полного переопределения hashCode:");

        for (User user : userSet) {
            System.out.println(user);
        }

        // i. Вывести пользователей на экран после полного переопределения hashCode
        System.out.println("Задание 4.i: Содержимое userSet после полного переопределения hashCode:");
        for (User user : userSet) {
            System.out.println(user);
        }
    }

    /**
     * Метод для вывода всех элементов Map в консоль.
     *
     * @param map Map с ключами типа Integer и значениями типа String.
     */
    public static void printMap(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("Месяц " + (entry.getKey() + 1) + ": " + entry.getValue());
        }
    }
}
