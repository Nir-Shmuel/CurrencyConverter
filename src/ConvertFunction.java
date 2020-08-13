public interface ConvertFunction<T, U> {
    U apply(T base, U rate);
}
