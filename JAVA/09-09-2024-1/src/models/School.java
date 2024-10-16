package models;

import enums.AccreditationLevel;
import enums.BuildingType;
import interfaces.Printable;
import exceptions.BuildingException;

import java.util.Random;

public class School extends Building {
    private AccreditationLevel accreditationLevel;
    private int numberOfStudents;

    public School(Address address, AccreditationLevel accreditationLevel) throws BuildingException {
        super(BuildingType.SCHOOL, address);
        this.accreditationLevel = accreditationLevel;
        this.numberOfStudents = generateStudentsCount(accreditationLevel);
    }

    private int generateStudentsCount(AccreditationLevel level) {
        Random rand = new Random();
        switch (level) {
            case GENERAL_EDUCATION:
                return rand.nextInt(50) + 100; // 100-149
            case GYMNASIUM:
                return rand.nextInt(50) + 150; // 150-199
            case LICEUM:
                return rand.nextInt(50) + 200; // 200-249
            default:
                return 0;
        }
    }

    // Геттеры и сеттеры
    public AccreditationLevel getAccreditationLevel() {
        return accreditationLevel;
    }

    public void setAccreditationLevel(AccreditationLevel accreditationLevel) throws BuildingException {
        this.accreditationLevel = accreditationLevel;
        this.numberOfStudents = generateStudentsCount(accreditationLevel);
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    // В данном случае сеттер для numberOfStudents не нужен, так как он зависит от accreditationLevel

    @Override
    public void printInfo() {
        System.out.println("Школа по адресу: " + address.toString() +
                ", Уровень аккредитации: " + accreditationLevel +
                ", Количество учащихся: " + numberOfStudents);
    }

    @Override
    public void setFieldsFromString(String data) throws BuildingException {
        // Формат: accreditationLevel=LEVEL
        String[] parts = data.split("=");
        if (parts.length != 2 || !parts[0].trim().equalsIgnoreCase("accreditationLevel")) {
            throw new BuildingException("Некорректные данные для School.");
        }
        try {
            AccreditationLevel level = AccreditationLevel.valueOf(parts[1].trim().toUpperCase());
            setAccreditationLevel(level);
        } catch (IllegalArgumentException e) {
            throw new BuildingException("Неизвестный уровень аккредитации: " + parts[1].trim());
        }
    }
}
