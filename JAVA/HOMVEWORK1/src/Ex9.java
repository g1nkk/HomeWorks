import java.util.Random;

public class Ex9 {
    public static void main(String[] args) {
        int[] array = new int[15];
        Random random = new Random();

        int min = 0;
        int max = 0;
        int negativeCount = 0;
        int positiveCount = 0;
        int zeroCount = 0;

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(21) - 10;
            System.out.print(array[i] + " ");

            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < 0) {
                negativeCount++;
            } else if (array[i] > 0) {
                positiveCount++;
            } else {
                zeroCount++;
            }
        }

        System.out.println("");
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        System.out.println("negatives: " + negativeCount);
        System.out.println("positives: " + positiveCount);
        System.out.println("zeros: " + zeroCount);
    }
}
