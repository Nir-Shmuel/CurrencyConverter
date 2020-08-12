import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter<T extends Currency, V extends Currency> implements Converter<T, V> {
    private ConvertFunction<T, Double, Double> convertFunction;
    private RatioCalculator<T, V, Double> ratioCalculator;
    private Map<String, Double> ratesCache;

    public CurrencyConverter(ConvertFunction<T, Double, Double> convertFunction, RatioCalculator<T, V, Double> ratioCalculator) {
        this.convertFunction = convertFunction;
        this.ratioCalculator = ratioCalculator;
        ratesCache = new HashMap<>();
    }

    @Override
    public V convert(T base, V dest) throws Exception {
        if (base == null || dest == null) {
            throw new NullInputException();
        }
        Double rate;
        String key = String.format("%s%s", base.getId(), dest.getId());
        if (ratesCache.containsKey(key)) {
            rate = ratesCache.get(key);
        } else {
            rate = ratioCalculator.calculateRatio(base, dest);
            ratesCache.put(key, rate);
        }
        Double convertedValue = convertFunction.apply(base, rate);
        dest.setValue(convertedValue);
        return dest;
    }

}
