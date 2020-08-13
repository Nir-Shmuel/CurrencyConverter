import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testRatioCalculator() {
        RatioCalculator<String, Double> ratioCalculator = (base, dest) -> {
            if (base == null || dest == null)
                throw new NullInputException();
            return 3.5;
        };
        try {
            ratioCalculator.calculateRatio("USD", null);
        } catch (Exception e) {
            assertEquals("Can't handle null currency", e.getMessage());
        }
        try {
            Double ratio = ratioCalculator.calculateRatio("USD", "ILS");
            assertEquals(3.5, ratio, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCurrencyConverter() {
        ConvertFunction<Double, Double> function = new CurrencyConvertFunction();
        RatioCalculator<String, Double> calculator = (base, dest) -> {
            if (base == null || dest == null)
                throw new NullInputException();
            return 3.5;
        };
        Converter<String, Double> converter = new CurrencyConverter(function, calculator);
        try {
            converter.convert("USD", null, null);
        } catch (Exception e) {
            assertEquals("Can't handle null currency", e.getMessage());
        }
        try {
            List<Double> values = new LinkedList<>();
            values.add(1.);
            assertEquals(3.5, converter.convert("UDS", "ILS", values).get(0), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
