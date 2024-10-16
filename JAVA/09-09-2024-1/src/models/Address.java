package models;

import exceptions.AddressException;

public class Address {
    private String streetName;
    private int houseNumber;

    public Address(String streetName, int houseNumber) throws AddressException {
        if (streetName == null || streetName.isEmpty()) {
            throw new AddressException("Название улицы не может быть пустым.");
        }
        if (houseNumber <= 0) {
            throw new AddressException("Номер дома должен быть положительным.");
        }
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    // Геттеры и сеттеры
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) throws AddressException {
        if (streetName == null || streetName.isEmpty()) {
            throw new AddressException("Название улицы не может быть пустым.");
        }
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) throws AddressException {
        if (houseNumber <= 0) {
            throw new AddressException("Номер дома должен быть положительным.");
        }
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return streetName + " " + houseNumber;
    }
}
