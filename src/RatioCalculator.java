public interface RatioCalculator<T, V, R> {
    R calculateRatio(T base, V dest) throws Exception;
}
