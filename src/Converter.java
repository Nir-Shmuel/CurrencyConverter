import java.util.List;

public interface Converter<T, V> {
    List<V> convert(T base, T dest, List<V> values) throws Exception;
}
