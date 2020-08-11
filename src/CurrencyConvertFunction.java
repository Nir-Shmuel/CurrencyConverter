public class CurrencyConvertFunction<T extends Currency> implements ConvertFunction<T, Double, Double> {
    @Override
    public Double apply(T base, Double rate) {
        return base.getValue() * rate;
    }
}
