package wrappers;
// интерфейс для создания wrapper объектов
public interface WrapperFactory<T> {
    T createFromPrimitive(T primitive);
    T createFromValueOf(String value);
    T createUsingNew(T primitive);
    T createFromString(String value);
}
