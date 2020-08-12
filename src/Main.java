import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String srcFile = args[0];
        try (Scanner scanner = new Scanner(new FileInputStream(srcFile))) {
            String base = scanner.nextLine();
            String dest = scanner.nextLine();

            Currency baseCurrency = new Currency(base);
            Currency destCurrency = new Currency(dest);

            ConvertFunction<Currency, Double, Double> convertFunction = new CurrencyConvertFunction<>();
            RatioCalculator<Currency, Currency, Double> ratioCalculator = new ExchangeRateCalculator<>();
            Converter<Currency, Currency> converter = new CurrencyConverter<>(convertFunction, ratioCalculator);

            while (scanner.hasNextLine()) {
                Double baseValue = Double.parseDouble(scanner.nextLine());
                baseCurrency.setValue(baseValue);
                try {
                    converter.convert(baseCurrency, destCurrency);
                    System.out.println(destCurrency.getValue());
                } catch (Exception e) {
                    System.out.println(String.format("Failed to convert %s to %s. Please try again later.", base, dest));
                    System.exit(-1);
                }
            }

        } catch (IOException e) {
            System.out.println("Invalid input.");
            System.exit(-1);
        }
    }
}
