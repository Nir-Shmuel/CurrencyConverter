import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter<T extends Currency, V extends Currency> implements Converter<T, V> {
    private ConvertFunction<T, Double, Double> convertFunction;
    private RatioCalculator<Currency, Currency, Double> ratioCalculator;
    private Map<String, Double> rates;

    public CurrencyConverter() {
        convertFunction = new CurrencyConvertFunction<>();
        ratioCalculator = new ExchangeRateCalculator<>();
        rates = new HashMap<>();
    }

    @Override
    public V convert(T base, V dest) throws Exception {
        if (base == null || dest == null) {
            throw new NullInputException();
        }
        Double rate;
        String key = String.format("%s%s", base.getId(), dest.getId());
        if (rates.containsKey(key)) {
            rate = rates.get(key);
        } else {
            rate = ratioCalculator.calculateRatio(base, dest);
            rates.put(key, rate);
        }
        Double convertedValue = convertFunction.apply(base, rate);
        dest.setValue(convertedValue);
        return dest;
    }

}
