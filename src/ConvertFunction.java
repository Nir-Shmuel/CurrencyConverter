public interface ConvertFunction<T, U, R> {
    R apply(T base, U rate);
}
