package ui;

import enums.DepartmentType;
import enums.StoreType;
import enums.AccreditationLevel;
import exceptions.AddressException;
import models.*;
import exceptions.BuildingException;
import exceptions.StreetException;

import java.util.*;

public class StreetMenu {
    private Street street;
    private Scanner scanner;

    public StreetMenu() {
        scanner = new Scanner(System.in);
        try {
            street = Factory.createTestStreet();
        } catch (BuildingException e) {
            System.err.println("Ошибка инициализации улицы: " + e.getMessage());
            street = null;
        }
    }

    public void start() {
        if (street == null) {
            System.out.println("Не удалось инициализировать улицу.");
            return;
        }

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    street.printStreetInfo();
                    break;
                case "2":
                    addBuilding();
                    break;
                case "3":
                    removeBuilding();
                    break;
                case "4":
                    setAddress();
                    break;
                case "5":
                    searchStores();
                    break;
                case "6":
                    setFieldsFromString();
                    break;
                case "7":
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Меню Улицы ===");
        System.out.println("1. Показать все дома на улице");
        System.out.println("2. Добавить дом");
        System.out.println("3. Удалить дом");
        System.out.println("4. Установить адрес дома");
        System.out.println("5. Найти магазины в окрестности жилого дома с заданным типом отдела");
        System.out.println("6. Установить поля дома через строку");
        System.out.println("7. Выход");
        System.out.print("Выберите опцию: ");
    }

    private void addBuilding() {
        System.out.println("\nВыберите тип дома для добавления:");
        System.out.println("1. Жилой дом");
        System.out.println("2. Магазин");
        System.out.println("3. Больница");
        System.out.println("4. Школа");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine().trim();

        try {
            System.out.print("Введите название улицы: ");
            String streetName = scanner.nextLine().trim();
            System.out.print("Введите номер дома: ");
            int houseNumber = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case "1":
                    System.out.print("Введите количество жильцов: ");
                    int residents = Integer.parseInt(scanner.nextLine().trim());
                    ResidentialHouse residentialHouse = Factory.createResidentialHouse(streetName, houseNumber, residents);
                    street.addBuilding(residentialHouse);
                    System.out.println("Жилой дом добавлен.");
                    break;
                case "2":
                    System.out.println("Выберите тип магазина:");
                    System.out.println("1. Маленький магазин");
                    System.out.println("2. Супермаркет");
                    System.out.print("Ваш выбор: ");
                    String storeChoice = scanner.nextLine().trim();
                    StoreType storeType = storeChoice.equals("1") ? StoreType.SMALL : StoreType.SUPERMARKET;

                    Set<DepartmentType> departments = new HashSet<>();
                    if (storeType == StoreType.SMALL) {
                        departments.add(DepartmentType.GROCERY);
                    } else {
                        departments.addAll(Arrays.asList(DepartmentType.GROCERY, DepartmentType.ELECTRONICS,
                                DepartmentType.CLOTHING, DepartmentType.PHARMACY, DepartmentType.TOYS));
                    }

                    Store store = Factory.createStore(streetName, houseNumber, storeType, departments);
                    street.addBuilding(store);
                    System.out.println("Магазин добавлен.");
                    break;
                case "3":
                    System.out.print("Введите количество коек: ");
                    int beds = Integer.parseInt(scanner.nextLine().trim());
                    Hospital hospital = Factory.createHospital(streetName, houseNumber, beds);
                    street.addBuilding(hospital);
                    System.out.println("Больница добавлена.");
                    break;
                case "4":
                    System.out.println("Выберите уровень аккредитации школы:");
                    System.out.println("1. Общеобразовательная");
                    System.out.println("2. Гимназия");
                    System.out.println("3. Лицей");
                    System.out.print("Ваш выбор: ");
                    String levelChoice = scanner.nextLine().trim();
                    AccreditationLevel level;
                    switch (levelChoice) {
                        case "1":
                            level = AccreditationLevel.GENERAL_EDUCATION;
                            break;
                        case "2":
                            level = AccreditationLevel.GYMNASIUM;
                            break;
                        case "3":
                            level = AccreditationLevel.LICEUM;
                            break;
                        default:
                            System.out.println("Некорректный выбор уровня аккредитации.");
                            return;
                    }
                    School school = Factory.createSchool(streetName, houseNumber, level);
                    street.addBuilding(school);
                    System.out.println("Школа добавлена.");
                    break;
                default:
                    System.out.println("Некорректный выбор типа дома.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат числа. Попробуйте снова.");
        } catch (BuildingException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void removeBuilding() {
        try {
            System.out.print("\nВведите название улицы: ");
            String streetName = scanner.nextLine().trim();
            System.out.print("Введите номер дома для удаления: ");
            int houseNumber = Integer.parseInt(scanner.nextLine().trim());
            Address address = new Address(streetName, houseNumber);
            street.removeBuilding(address);
            System.out.println("Дом удален.");
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат числа. Попробуйте снова.");
        } catch (AddressException | StreetException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void setAddress() {
        try {
            System.out.print("\nВведите текущее название улицы: ");
            String currentStreet = scanner.nextLine().trim();
            System.out.print("Введите текущий номер дома: ");
            int currentHouseNumber = Integer.parseInt(scanner.nextLine().trim());
            Address currentAddress = new Address(currentStreet, currentHouseNumber);

            System.out.print("Введите новое название улицы: ");
            String newStreet = scanner.nextLine().trim();
            System.out.print("Введите новый номер дома: ");
            int newHouseNumber = Integer.parseInt(scanner.nextLine().trim());
            Address newAddress = new Address(newStreet, newHouseNumber);

            // Найти дом
            Building buildingToUpdate = null;
            for (Building b : street.getBuildings()) {
                if (b.getAddress().equals(currentAddress)) {
                    buildingToUpdate = b;
                    break;
                }
            }
            if (buildingToUpdate == null) {
                System.out.println("Дом с таким адресом не найден.");
                return;
            }

            // Обновить адрес
            buildingToUpdate.setAddress(newAddress);
            System.out.println("Адрес дома обновлен.");
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат числа. Попробуйте снова.");
        } catch (AddressException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void searchStores() {
        try {
            System.out.print("\nВведите название улицы жилого дома: ");
            String streetName = scanner.nextLine().trim();
            System.out.print("Введите номер дома жилого дома: ");
            int houseNumber = Integer.parseInt(scanner.nextLine().trim());
            Address residentialAddress = new Address(streetName, houseNumber);

            System.out.print("Введите радиус окрестности (количество домов от адреса): ");
            int vicinity = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Выберите тип отдела магазина:");
            for (DepartmentType dept : DepartmentType.values()) {
                System.out.println("- " + dept);
            }
            System.out.print("Ваш выбор: ");
            String deptInput = scanner.nextLine().trim().toUpperCase();
            DepartmentType deptType = DepartmentType.valueOf(deptInput);

            List<Store> nearbyStores = street.findNearbyStores(residentialAddress, vicinity, deptType);
            if (nearbyStores.isEmpty()) {
                System.out.println("Магазины с отделом " + deptType + " в окрестности не найдены.");
            } else {
                System.out.println("Найденные магазины:");
                for (Store store : nearbyStores) {
                    store.printInfo();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат числа. Попробуйте снова.");
        } catch (AddressException e) {
            System.out.println("Ошибка адреса: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный тип отдела.");
        }
    }

    private void setFieldsFromString() {
        try {
            System.out.println("\nВыберите тип дома для установки полей через строку:");
            System.out.println("1. Жилой дом");
            System.out.println("2. Магазин");
            System.out.println("3. Больница");
            System.out.println("4. Школа");
            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine().trim();

            System.out.print("Введите название улицы дома: ");
            String streetName = scanner.nextLine().trim();
            System.out.print("Введите номер дома: ");
            int houseNumber = Integer.parseInt(scanner.nextLine().trim());
            Address address = new Address(streetName, houseNumber);

            // Найти дом
            Building buildingToUpdate = null;
            for (Building b : street.getBuildings()) {
                if (b.getAddress().equals(address)) {
                    buildingToUpdate = b;
                    break;
                }
            }
            if (buildingToUpdate == null) {
                System.out.println("Дом с таким адресом не найден.");
                return;
            }

            System.out.println("Введите строку для установки полей:");
            String data = scanner.nextLine().trim();

            // Установить поля
            buildingToUpdate.setFieldsFromString(data);
            System.out.println("Поля дома обновлены.");

        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат числа. Попробуйте снова.");
        } catch (BuildingException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
