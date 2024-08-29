import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Meters: ");
        double meters = scanner.nextDouble();

        System.out.println("Select a unit of measurement: 1 - miles, 2 - inches, 3 - yards");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println(meters + " meters = " + meters * 0.000621371 + " miles");
        } else if (choice == 2) {
            System.out.println(meters + " meters = " + meters * 39.3701 + " inches");
        } else if (choice == 3) {
            System.out.println(meters + " meters = " + meters * 1.09361 + " yards");
        } else {
            System.out.println("Error");
        }
    }
}
