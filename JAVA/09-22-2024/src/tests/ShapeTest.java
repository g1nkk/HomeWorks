package tests;

import org.junit.jupiter.api.Test;
import shapes.Rectangle;
import shapes.Rhombus;
import shapes.Square;
import shapes.Triangle;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {
    @Test
    public void testTriangleArea() {
        Triangle triangle = new Triangle(10, 5);
        assertEquals(25, triangle.calculateArea(), 0.001);
    }

    @Test
    public void testRectangleArea() {
        Rectangle rectangle = new Rectangle(10, 5);
        assertEquals(50, rectangle.calculateArea(), 0.001);
    }

    @Test
    public void testSquareArea() {
        Square square = new Square(4);
        assertEquals(16, square.calculateArea(), 0.001);
    }

    @Test
    public void testRhombusArea() {
        Rhombus rhombus = new Rhombus(6, 8);
        assertEquals(24, rhombus.calculateArea(), 0.001);
    }
}
