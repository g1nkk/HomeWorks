package models;

import enums.AccreditationLevel;
import enums.DepartmentType;
import enums.StoreType;
import exceptions.BuildingException;

import java.util.HashSet;
import java.util.Set;

public class Factory {
    // Метод для создания ResidentialHouse
    public static ResidentialHouse createResidentialHouse(String streetName, int houseNumber, int residents) throws BuildingException {
        Address address = new Address(streetName, houseNumber);
        return new ResidentialHouse(address, residents);
    }

    // Метод для создания Store
    public static Store createStore(String streetName, int houseNumber, StoreType storeType, Set<DepartmentType> departments) throws BuildingException {
        Address address = new Address(streetName, houseNumber);
        return new Store(address, storeType, departments);
    }

    // Метод для создания Hospital
    public static Hospital createHospital(String streetName, int houseNumber, int beds) throws BuildingException {
        Address address = new Address(streetName, houseNumber);
        return new Hospital(address, beds);
    }

    // Метод для создания School
    public static School createSchool(String streetName, int houseNumber, AccreditationLevel level) throws BuildingException {
        Address address = new Address(streetName, houseNumber);
        return new School(address, level);
    }

    // Метод для создания улицы с тестовыми домами
    public static Street createTestStreet() throws BuildingException {
        Street street = new Street("Main Street");

        // Создание жилых домов
        street.addBuilding(createResidentialHouse("Main Street", 1, 4));
        street.addBuilding(createResidentialHouse("Main Street", 3, 5));

        // Создание магазинов
        Set<DepartmentType> departments1 = new HashSet<>();
        departments1.add(DepartmentType.GROCERY);
        departments1.add(DepartmentType.ELECTRONICS);
        street.addBuilding(createStore("Main Street", 2, StoreType.SUPERMARKET, departments1));

        Set<DepartmentType> departments2 = new HashSet<>();
        departments2.add(DepartmentType.CLOTHING);
        street.addBuilding(createStore("Main Street", 4, StoreType.SMALL, departments2));

        // Создание больницы
        street.addBuilding(createHospital("Main Street", 5, 100));

        // Создание школ
        street.addBuilding(createSchool("Main Street", 6, AccreditationLevel.GYMNASIUM));
        street.addBuilding(createSchool("Main Street", 7, AccreditationLevel.LICEUM));

        return street;
    }
}
