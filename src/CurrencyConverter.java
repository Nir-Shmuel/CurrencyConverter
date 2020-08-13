import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrencyConverter implements Converter<String, Double> {
    private ConvertFunction<Double, Double> convertFunction;
    private RatioCalculator<String, Double> ratioCalculator;
    private Map<String, Double> ratesCache;

    public CurrencyConverter(ConvertFunction<Double, Double> convertFunction, RatioCalculator<String, Double> ratioCalculator) {
        this.convertFunction = convertFunction;
        this.ratioCalculator = ratioCalculator;
        ratesCache = new HashMap<>();
    }

    @Override
    public List<Double> convert(String base, String dest, List<Double> values) throws Exception {
        if (base == null || dest == null) {
            throw new NullInputException();
        }
        String key = String.format("%s%s", base, dest);
        Double rate = ratesCache.get(key);
        if (rate == null) {
            rate = ratioCalculator.calculateRatio(base, dest);
            ratesCache.put(key, rate);
        }
        final Double finalRate= rate;
        return values.stream().map(v->convertFunction.apply(v, finalRate)).collect(Collectors.toList());
    }
}
