package models;

import enums.BuildingType;
import interfaces.Printable;
import exceptions.BuildingException;

public class Hospital extends Building {
    private int numberOfBeds;

    public Hospital(Address address, int numberOfBeds) throws BuildingException {
        super(BuildingType.HOSPITAL, address);
        if (numberOfBeds <= 0) {
            throw new BuildingException("Количество коек должно быть положительным.");
        }
        this.numberOfBeds = numberOfBeds;
    }

    // Геттеры и сеттеры
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) throws BuildingException {
        if (numberOfBeds <= 0) {
            throw new BuildingException("Количество коек должно быть положительным.");
        }
        this.numberOfBeds = numberOfBeds;
    }

    @Override
    public void printInfo() {
        System.out.println("Больница по адресу: " + address.toString() +
                ", Количество коек: " + numberOfBeds);
    }

    @Override
    public void setFieldsFromString(String data) throws BuildingException {
        // Формат: numberOfBeds=NUMBER
        String[] parts = data.split("=");
        if (parts.length != 2 || !parts[0].trim().equalsIgnoreCase("numberOfBeds")) {
            throw new BuildingException("Некорректные данные для Hospital.");
        }
        try {
            int beds = Integer.parseInt(parts[1].trim());
            setNumberOfBeds(beds);
        } catch (NumberFormatException e) {
            throw new BuildingException("Неверный формат числа коек.");
        }
    }
}
