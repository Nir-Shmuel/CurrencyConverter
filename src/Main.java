import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String srcFile = args[0];
        try (Scanner scanner = new Scanner(new FileInputStream(srcFile))) {
            String base = scanner.nextLine();
            String dest = scanner.nextLine();

            ConvertFunction<Double, Double> convertFunction = new CurrencyConvertFunction();
            RatioCalculator<String, Double> ratioCalculator = new ExchangeRateCalculator();
            Converter<String, Double> converter = new CurrencyConverter(convertFunction, ratioCalculator);

            List<Double> values = Files.lines(Paths.get(srcFile)).skip(2).map(Double::parseDouble).collect(Collectors.toList());
            try {
                converter.convert(base, dest, values).forEach(System.out::println);
            } catch (Exception e) {
                System.out.println(String.format("Failed to convert %s to %s. Please try again later.", base, dest));
                System.exit(-1);
            }


        } catch (IOException e) {
            System.out.println("Invalid input.");
            System.exit(-1);
        }
    }
}
