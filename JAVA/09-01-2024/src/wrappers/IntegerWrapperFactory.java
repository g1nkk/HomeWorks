package wrappers;


/**
 * фабрика для создания объектов Integer
 */
public class IntegerWrapperFactory implements WrapperFactory<Integer> {

    @Override
    public Integer createFromPrimitive(Integer primitive) {
        // Автоупаковка
        return primitive;
    }

    @Override
    public Integer createFromValueOf(String value) {
        return Integer.valueOf(value);
    }

    @Override
    public Integer createUsingNew(Integer primitive) {
        return new Integer(primitive);
    }

    @Override
    public Integer createFromString(String value) {
        return Integer.parseInt(value);
    }
}
