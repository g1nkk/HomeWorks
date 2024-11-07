package currencyConvert;

public class CurrencyConverter {
    private static final double USD_TO_EUR = 0.9;
    private static final double USD_TO_GBP = 0.75;
    private static final double USD_TO_JPY = 110;

    public double convertUsdToEur(double amount) {
        return amount * USD_TO_EUR;
    }

    public double convertUsdToGbp(double amount) {
        return amount * USD_TO_GBP;
    }

    public double convertUsdToJpy(double amount) {
        return amount * USD_TO_JPY;
    }
}