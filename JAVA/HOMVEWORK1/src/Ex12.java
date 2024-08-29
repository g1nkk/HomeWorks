import java.util.Arrays;
import java.util.Scanner;

public class Ex12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = {5, 3, 8, 1, 2};

        System.out.println("1 - по возрастанию 2 - по убыванию");
        int choice = scanner.nextInt();

        if (choice == 1) {
            Arrays.sort(array);
        } else if (choice == 2) {
            Arrays.sort(array);
            for (int i = 0; i < array.length / 2; i++) {
                int temp = array[i];
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }
        } else {
            System.out.println("Error");
        }

        System.out.println(Arrays.toString(array));
    }
}
