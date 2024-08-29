import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.print("Enter 6 length number:");
        int number = scanner.nextInt();

        if(String.valueOf(number).length() != 6){
            System.out.println("Length of number is not equal 6!");
            main(args);
        }

        char[] tempArray = String.valueOf(number).toCharArray();
        char[] finalArray = String.valueOf(number).toCharArray();

        finalArray[0] = tempArray[5];
        finalArray[5] = tempArray[0];

        finalArray[1] = tempArray[4];
        finalArray[4] = tempArray[1];

        System.out.println("Final Number = " + String.valueOf(finalArray));
    }
}
