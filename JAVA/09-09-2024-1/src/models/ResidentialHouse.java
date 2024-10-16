package models;

import enums.BuildingType;
import interfaces.Printable;
import exceptions.BuildingException;

public class ResidentialHouse extends Building {
    private int numberOfResidents;

    public ResidentialHouse(Address address, int numberOfResidents) throws BuildingException {
        super(BuildingType.RESIDENTIAL, address);
        if (numberOfResidents < 0) {
            throw new BuildingException("Количество жильцов не может быть отрицательным.");
        }
        this.numberOfResidents = numberOfResidents;
    }

    // Геттеры и сеттеры
    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public void setNumberOfResidents(int numberOfResidents) throws BuildingException {
        if (numberOfResidents < 0) {
            throw new BuildingException("Количество жильцов не может быть отрицательным.");
        }
        this.numberOfResidents = numberOfResidents;
    }

    @Override
    public void printInfo() {
        System.out.println("Жилой дом по адресу: " + address.toString() +
                ", Количество жильцов: " + numberOfResidents);
    }

    @Override
    public void setFieldsFromString(String data) throws BuildingException {
        // Формат: numberOfResidents=NUMBER
        String[] parts = data.split("=");
        if (parts.length != 2 || !parts[0].trim().equalsIgnoreCase("numberOfResidents")) {
            throw new BuildingException("Некорректные данные для ResidentialHouse.");
        }
        try {
            int residents = Integer.parseInt(parts[1].trim());
            setNumberOfResidents(residents);
        } catch (NumberFormatException e) {
            throw new BuildingException("Неверный формат числа жильцов.");
        }
    }
}
