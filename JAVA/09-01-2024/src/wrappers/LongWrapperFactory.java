package wrappers;

/**
 * фабрика для создания объектов Long
 */
public class LongWrapperFactory implements WrapperFactory<Long> {

    @Override
    public Long createFromPrimitive(Long primitive) {
        // Автоупаковка
        return primitive;
    }

    @Override
    public Long createFromValueOf(String value) {
        return Long.valueOf(value);
    }

    @Override
    public Long createUsingNew(Long primitive) {
        return new Long(primitive);
    }

    @Override
    public Long createFromString(String value) {
        return Long.parseLong(value);
    }
}
