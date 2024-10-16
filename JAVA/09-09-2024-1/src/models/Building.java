package models;

import enums.BuildingType;
import interfaces.Printable;
import exceptions.BuildingException;
import exceptions.AddressException;

public abstract class Building implements Printable {
    protected Address address;
    protected final BuildingType type;

    public Building(BuildingType type, Address address) throws AddressException {
        this.type = type;
        this.address = address;
    }

    // Геттеры и сеттеры
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) throws AddressException {
        this.address = address;
    }

    public BuildingType getType() {
        return type;
    }

    // Абстрактный метод для виртуального метода
    public abstract void setFieldsFromString(String data) throws BuildingException;
}
