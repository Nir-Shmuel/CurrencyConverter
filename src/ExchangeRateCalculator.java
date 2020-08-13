import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateCalculator implements RatioCalculator<String, Double> {
    private final String host = "https://api.exchangeratesapi.io/latest";
    private final String urlFormat = "%s?base=%s";


    /**
     * Assumption: The inputs are valid.
     */
    @Override
    public Double calculateRatio(String base, String dest) throws Exception {
        if (base == null || dest == null) {
            throw new NullInputException();
        }
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = String.format(urlFormat, host, base);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .version(HttpClient.Version.HTTP_1_1)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsObject = new JSONObject(response.body());
            JSONObject rates = jsObject.getJSONObject("rates");

            return Double.parseDouble(rates.get(dest).toString());
        } catch (JSONException e) {
            throw new Exception(String.format("Failed to find currency id: %s", dest), e);
        } catch (IOException | InterruptedException e) {
            throw new Exception("Failed to convert the currencies. Please try again later.", e);
        }

    }

}
