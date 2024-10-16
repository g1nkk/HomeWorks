package models;

import enums.BuildingType;
import enums.DepartmentType;
import exceptions.StreetException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Street {
    private String streetName;
    private List<Building> buildings;

    public Street(String streetName) throws StreetException {
        if (streetName == null || streetName.isEmpty()) {
            throw new StreetException("Название улицы не может быть пустым.");
        }
        this.streetName = streetName;
        this.buildings = new ArrayList<>();
    }

    // Геттеры и сеттеры
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) throws StreetException {
        if (streetName == null || streetName.isEmpty()) {
            throw new StreetException("Название улицы не может быть пустым.");
        }
        this.streetName = streetName;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    // Метод для добавления дома
    public void addBuilding(Building building) throws StreetException {
        // Проверка уникальности адреса
        for (Building b : buildings) {
            if (b.getAddress().equals(building.getAddress())) {
                throw new StreetException("Дом с таким адресом уже существует.");
            }
        }
        buildings.add(building);
    }

    // Метод для удаления дома по адресу
    public void removeBuilding(Address address) throws StreetException {
        boolean removed = buildings.removeIf(b -> b.getAddress().equals(address));
        if (!removed) {
            throw new StreetException("Дом с таким адресом не найден.");
        }
    }

    // Метод вывода информации о всех домах
    public void printStreetInfo() {
        System.out.println("Улица: " + streetName);
        for (Building building : buildings) {
            building.printInfo();
        }
    }

    // Метод поиска магазинов в окрестности жилого дома с заданным типом отдела
    public List<Store> findNearbyStores(Address residentialAddress, int vicinity, DepartmentType deptType) {
        // Найти индекс жилого дома
        int index = -1;
        for (int i = 0; i < buildings.size(); i++) {
            Building b = buildings.get(i);
            if (b.getType() == BuildingType.RESIDENTIAL && b.getAddress().equals(residentialAddress)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return new ArrayList<>(); // Жилого дома нет
        }
        // Определить диапазон окрестности
        int start = Math.max(0, index - vicinity);
        int end = Math.min(buildings.size() - 1, index + vicinity);
        // Собрать магазины с нужным отделом
        return buildings.subList(start, end + 1).stream()
                .filter(b -> b.getType() == BuildingType.STORE)
                .map(b -> (Store) b)
                .filter(store -> store.getDepartments().contains(deptType))
                .collect(Collectors.toList());
    }
}
