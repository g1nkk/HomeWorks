import java.util.ArrayList;
import java.util.Random;

public class Ex10 {
    public static void main(String[] args) {
        int[] array = new int[10];
        Random random = new Random();

        ArrayList<Integer> evenNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        ArrayList<Integer> negativeNumbers = new ArrayList<>();
        ArrayList<Integer> positiveNumbers = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(21) - 10;
            System.out.print(array[i] + " ");

            if (array[i] % 2 == 0) {
                evenNumbers.add(array[i]);
            } else {
                oddNumbers.add(array[i]);
            }

            if (array[i] < 0) {
                negativeNumbers.add(array[i]);
            } else if (array[i] > 0) {
                positiveNumbers.add(array[i]);
            }
        }

        System.out.println("even numbers: " + evenNumbers);
        System.out.println("odd numbers: " + oddNumbers);
        System.out.println("negative numbers: " + negativeNumbers);
        System.out.println("positive numbers: " + positiveNumbers);
    }
}
