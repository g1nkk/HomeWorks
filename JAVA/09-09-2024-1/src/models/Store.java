package models;

import enums.BuildingType;
import enums.StoreType;
import enums.DepartmentType;
import interfaces.Printable;
import exceptions.BuildingException;

import java.util.HashSet;
import java.util.Set;

public class Store extends Building {
    private StoreType storeType;
    private Set<DepartmentType> departments;

    public Store(Address address, StoreType storeType, Set<DepartmentType> departments) throws BuildingException {
        super(BuildingType.STORE, address);
        if (departments == null || departments.isEmpty()) {
            throw new BuildingException("Магазин должен иметь хотя бы один отдел.");
        }
        this.storeType = storeType;
        this.departments = departments;
    }

    // Геттеры и сеттеры
    public StoreType getStoreType() {
        return storeType;
    }

    public void setStoreType(StoreType storeType) {
        this.storeType = storeType;
    }

    public Set<DepartmentType> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentType> departments) throws BuildingException {
        if (departments == null || departments.isEmpty()) {
            throw new BuildingException("Магазин должен иметь хотя бы один отдел.");
        }
        this.departments = departments;
    }

    @Override
    public void printInfo() {
        System.out.println("Магазин по адресу: " + address.toString() +
                ", Тип: " + storeType +
                ", Отделы: " + departments);
    }

    @Override
    public void setFieldsFromString(String data) throws BuildingException {
        // Формат: storeType=TYPE;departments=DEPT1,DEPT2,...
        String[] parts = data.split(";");
        if (parts.length != 2) {
            throw new BuildingException("Некорректные данные для Store.");
        }
        // Обработка storeType
        String[] storeTypeParts = parts[0].split("=");
        if (storeTypeParts.length != 2 || !storeTypeParts[0].trim().equalsIgnoreCase("storeType")) {
            throw new BuildingException("Некорректный тип магазина.");
        }
        try {
            StoreType type = StoreType.valueOf(storeTypeParts[1].trim().toUpperCase());
            setStoreType(type);
        } catch (IllegalArgumentException e) {
            throw new BuildingException("Неизвестный тип магазина.");
        }
        // Обработка departments
        String[] deptParts = parts[1].split("=");
        if (deptParts.length != 2 || !deptParts[0].trim().equalsIgnoreCase("departments")) {
            throw new BuildingException("Некорректные отделы магазина.");
        }
        String[] deptArray = deptParts[1].split(",");
        Set<DepartmentType> deptSet = new HashSet<>();
        for (String dept : deptArray) {
            try {
                deptSet.add(DepartmentType.valueOf(dept.trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new BuildingException("Неизвестный тип отдела: " + dept);
            }
        }
        setDepartments(deptSet);
    }
}
