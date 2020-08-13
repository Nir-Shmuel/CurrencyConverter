public interface RatioCalculator<T, R> {
    R calculateRatio(T base, T dest) throws Exception;
}
