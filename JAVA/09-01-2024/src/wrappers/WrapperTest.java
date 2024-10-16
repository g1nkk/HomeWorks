package wrappers;

public class WrapperTest {
    public static void main(String[] args) {
        WrapperFactory<Integer> integerFactory = new IntegerWrapperFactory();
        WrapperFactory<Double> doubleFactory = new DoubleWrapperFactory();
        WrapperFactory<Long> longFactory = new LongWrapperFactory();

        // 2.1 Создание и инициализация переменных для Integer
        Integer i1 = integerFactory.createFromPrimitive(10);         // Автоупаковка
        Integer i2 = integerFactory.createFromValueOf("10");         // valueOf
        Integer i3 = integerFactory.createUsingNew(10);      // new
        Integer i4 = integerFactory.createFromString("10");    // Парсинг из строки

        System.out.println("Integer Values:");
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
        System.out.println("i3 = " + i3);
        System.out.println("i4 = " + i4);

        // 2.1 Создание и инициализация переменных для Double
        Double d1 = doubleFactory.createFromPrimitive(10.5);             // Автоупаковка
        Double d2 = doubleFactory.createFromValueOf("10.5");             // valueOf
        Double d3 = doubleFactory.createUsingNew(10.5);          // new
        Double d4 = doubleFactory.createFromString("10.5");        // Парсинг из строки

        System.out.println("\nDouble Values:");
        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d3 = " + d3);
        System.out.println("d4 = " + d4);

        // 2.1 Создание и инициализация переменных для Long
        Long l1 = longFactory.createFromPrimitive(120L);                  // Автоупаковка
        Long l2 = longFactory.createFromValueOf("120");                   // valueOf
        Long l3 = longFactory.createUsingNew(120L);               // new
        Long l4 = longFactory.createFromString("120");              // Парсинг из строки

        System.out.println("\nLong Values:");
        System.out.println("l1 = " + l1);
        System.out.println("l2 = " + l2);
        System.out.println("l3 = " + l3);
        System.out.println("l4 = " + l4);

        // 3.1 Создание переменной типа Double с дробной частью
        Double doubleValue = 123.456;

        // 3.2 Преобразование и присвоение значения Double примитивным типам
        byte b = doubleValue.byteValue();
        short s = doubleValue.shortValue();
        int i = doubleValue.intValue();
        float f = doubleValue.floatValue();
        long l = doubleValue.longValue();

        System.out.println("\nPrimitive Conversions from Double (123.456):");
        System.out.println("byte: " + b);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("float: " + f);
        System.out.println("long: " + l);

        // 4. Проверка значений NaN и Infinity
        Double nonZero = 10.0;
        Double zero = 0.0;
        Double nanValue = nonZero / zero;        // Деление ненулевой переменной на ноль -> Infinity
        Double infinityValue = zero / zero;      // Деление нуля на ноль -> NaN

        System.out.println("\nSpecial Double Values:");
        System.out.println("nanValue = " + nanValue);
        System.out.println("infinityValue = " + infinityValue);

        // 4.5 Проверки и вывод соответствующих сообщений
        if (nanValue.isNaN()) {
            System.out.println("Переменная nanValue = NaN");
        }
        if (nanValue.isInfinite()) {
            System.out.println("Переменная nanValue = Infinity");
        }

        if (infinityValue.isNaN()) {
            System.out.println("Переменная infinityValue = NaN");
        }
        if (infinityValue.isInfinite()) {
            System.out.println("Переменная infinityValue = Infinity");
        }

        // 5. Сравнение Long переменных
        Long long1 = 120L;
        Long long2 = 120L;

        System.out.println("\nСравнение Long:");
        System.out.println("long1 == long2: " + (long1 == long2)); // true из-за кеширования

        long1 = 1200L;
        long2 = 1200L;

        System.out.println("long1 == long2: " + (long1 == long2)); // false, разные объекты
    }
}
