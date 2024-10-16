package ex5;

import java.io.Serializable;

public class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private String position;
    private double salary;

    public Employee(String firstName, String lastName, int age, String position, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    // Геттеры и сеттеры
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Сотрудник{" +
                "Имя='" + firstName + '\'' +
                ", Фамилия='" + lastName + '\'' +
                ", Возраст=" + age +
                ", Должность='" + position + '\'' +
                ", Зарплата=" + salary +
                '}';
    }
}

