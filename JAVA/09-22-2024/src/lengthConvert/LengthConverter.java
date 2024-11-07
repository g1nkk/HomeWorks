package lengthConvert;

public class LengthConverter {
    public double toCentimeters(double meters) {
        return meters * 100;
    }

    public double toMillimeters(double meters) {
        return meters * 1000;
    }

    public double toDecimeters(double meters) {
        return meters * 10;
    }

    public double toKilometers(double meters) {
        return meters / 1000;
    }
}