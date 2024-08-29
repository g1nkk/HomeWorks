import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        var Scanner = new Scanner(System.in);

        System.out.println("Enter value");
        int value = Scanner.nextInt();

        System.out.println("Enter percentage");
        int percentage = Scanner.nextInt();

        float finalValue = value * (percentage/100f);

        System.out.println(percentage + "% of " + value + ": " + finalValue);
    }
}
