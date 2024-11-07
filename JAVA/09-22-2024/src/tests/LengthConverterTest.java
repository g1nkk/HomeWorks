package tests;

import lengthConvert.LengthConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LengthConverterTest {
    @Test
    public void testToCentimeters() {
        LengthConverter converter = new LengthConverter();
        assertEquals(100, converter.toCentimeters(1), 0.001);
    }

    @Test
    public void testToMillimeters() {
        LengthConverter converter = new LengthConverter();
        assertEquals(1000, converter.toMillimeters(1), 0.001);
    }

    @Test
    public void testToDecimeters() {
        LengthConverter converter = new LengthConverter();
        assertEquals(10, converter.toDecimeters(1), 0.001);
    }

    @Test
    public void testToKilometers() {
        LengthConverter converter = new LengthConverter();
        assertEquals(0.001, converter.toKilometers(1), 0.001);
    }
}
