package tests;

import currencyConvert.CurrencyConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {
    @Test
    public void testUsdToEurConversion() {
        CurrencyConverter converter = new CurrencyConverter();
        assertEquals(90, converter.convertUsdToEur(100), 0.001);
    }

    @Test
    public void testUsdToGbpConversion() {
        CurrencyConverter converter = new CurrencyConverter();
        assertEquals(75, converter.convertUsdToGbp(100), 0.001);
    }

    @Test
    public void testUsdToJpyConversion() {
        CurrencyConverter converter = new CurrencyConverter();
        assertEquals(11000, converter.convertUsdToJpy(100), 0.001);
    }
}
