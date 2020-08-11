public interface Converter<T, V> {
    V convert(T base, V dest) throws Exception;
}
