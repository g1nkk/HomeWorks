package shapes;

public class Rhombus {
    private double diagonal1;
    private double diagonal2;

    public Rhombus(double diagonal1, double diagonal2) {
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
    }

    public double calculateArea() {
        return 0.5 * diagonal1 * diagonal2;
    }
}