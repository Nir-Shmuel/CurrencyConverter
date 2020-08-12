import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testCurrency() {
        Currency c1 = new Currency("USD");
        Currency c2 = new Currency("USD");
        Currency c3 = new Currency("ILS");
        c1.setValue(2.5);
        assertEquals(2.5, c1.getValue(), 0);
        assertNotEquals(c2.getValue(), c1.getValue());
        assertEquals(c1, c2);
        assertNotEquals(c2, c3);
    }

    @Test
    public void testRatioCalculator() {
        RatioCalculator<Currency, Currency, Double> ratioCalculator = new ExchangeRateCalculator<>();
        try {
            ratioCalculator.calculateRatio(new Currency("USD"), null);
        } catch (Exception e) {
            assertEquals("Can't handle null currency", e.getMessage());
        }
        try {
            Double ratio = ratioCalculator.calculateRatio(new Currency("USD"), new Currency("ILS"));
            assertNotEquals(null, ratio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ratioCalculator.calculateRatio(new Currency("USD"), new Currency("AAA"));
        } catch (Exception e) {
            assertEquals("Failed to find currency id: AAA",e.getMessage());
        }

    }

    @Test
    public void testCurrencyConverter() {
        Converter<Currency, Currency> converter = new CurrencyConverter<>(new CurrencyConvertFunction<>(),new ExchangeRateCalculator<>());
        try {
            converter.convert(new Currency("USD"), null);
        } catch (Exception e) {
            assertEquals("Can't handle null currency", e.getMessage());
        }
        try {
            converter.convert(new Currency("UDS"), new Currency("ILS"));
        } catch (Exception e) {
            assertEquals("Failed to find currency id: ILS", e.getMessage());
        }
    }
}
