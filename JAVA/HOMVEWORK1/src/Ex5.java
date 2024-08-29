import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.print("Enter number of month:");
        int number = scanner.nextInt();

        if(number == 12 || number <= 2) {
            System.out.print("Winter");
        }
        else if(number >= 3 && number <= 5) {
            System.out.print("Spring");
        }
        else if(number >= 6 && number <= 8) {
            System.out.print("Summer");
        }
        else if(number >= 9 && number <= 11) {
            System.out.print("Autumn");
        }
        else {
            System.out.print("Error");
            main(args);
        }

    }
}
