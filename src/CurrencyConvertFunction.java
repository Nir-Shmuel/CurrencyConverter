public class CurrencyConvertFunction implements ConvertFunction<Double, Double> {
    @Override
    public Double apply(Double base, Double rate) {
        return base * rate;
    }
}
