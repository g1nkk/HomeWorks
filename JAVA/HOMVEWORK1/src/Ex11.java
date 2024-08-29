public class Ex11 {
    public static void main(String[] args) {
        drawLine(5, "horizontal", '$');
    }

    public static void drawLine(int length, String direction, char symbol) {
        if (direction.equals("horizontal")) {
            for (int i = 0; i < length; i++) {
                System.out.print(symbol);
            }
            System.out.println();
        } else if (direction.equals("vertical")) {
            for (int i = 0; i < length; i++) {
                System.out.println(symbol);
            }
        }
    }
}
