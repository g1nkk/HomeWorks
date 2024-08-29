import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        var Scanner = new Scanner(System.in);

        System.out.print("First Number:");
        int firstNumber = Scanner.nextInt();
        System.out.print("Second Number:");
        int secondNumber = Scanner.nextInt();
        System.out.print("Third Number:");
        int thirdNumber = Scanner.nextInt();

        String finalNumber = String.valueOf(firstNumber) + secondNumber + thirdNumber;
        System.out.print("Final Number: " + finalNumber);
    }
}
