package services;

import model.ExchangeRates;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

public class ExchangeRatesService {
    private static final String API_BASE_URL = "https://api.currencybeacon.com/v1/latest?api_key=bb017cfe3c7311e2f3c774f8af75c55a";
    private final RestTemplate restTemplate = new RestTemplate();

    public ExchangeRatesService() {
    }

    public ExchangeRates getLatest() throws Exception {
        ExchangeRates response;
        Map<String, String> params = Collections.singletonMap("category", "latest");

        try {
            response = restTemplate.getForObject(API_BASE_URL, ExchangeRates.class);
        } catch (Exception e) {
            throw new Exception("Cannot access API.");
        }
        return response;
    }

    public ExchangeRates getHistorical() {
        ExchangeRates historicalExchangeRates = new ExchangeRates();

        Map<String, String> params = Collections.singletonMap("category", "historical");

        try {
            historicalExchangeRates = restTemplate.getForObject(API_BASE_URL, ExchangeRates.class, params);
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return historicalExchangeRates;
    }
}
