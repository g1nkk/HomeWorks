package wrappers;

/**
 *
 *  фабрика для создания объектов Double
 */
public class DoubleWrapperFactory implements WrapperFactory<Double> {

    @Override
    public Double createFromPrimitive(Double primitive) {
        // Автоупаковка
        return primitive;
    }

    @Override
    public Double createFromValueOf(String value) {
        return Double.valueOf(value);
    }

    @Override
    public Double createUsingNew(Double primitive) {
        return new Double(primitive);
    }

    @Override
    public Double createFromString(String value) {
        return Double.parseDouble(value);
    }
}
