import java.util.Objects;

public class User {
    private String name;
    private int age;
    private String phone;

    public User(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    // Переопределение toString для удобного вывода
    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", phone='" + phone + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, phone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User other = (User) obj;
        return age == other.age &&
                Objects.equals(name, other.name) &&
                Objects.equals(phone, other.phone);
    }

}