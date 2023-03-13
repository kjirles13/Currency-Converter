package services;

import model.ExchangeRates;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

public class ExchangeRatesService {
    private final String baseUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private final String API_KEY = "bb017cfe3c7311e2f3c774f8af75c55a";

    public ExchangeRatesService(String url) {
        this.baseUrl = url;
    }

    public ExchangeRates getLatest() {
        ExchangeRates exchangeRates = new ExchangeRates();

        Map<String, String> params = Collections.singletonMap("category", "latest");
        params.put("key", API_KEY);

        try {
            exchangeRates = restTemplate.getForObject(baseUrl, ExchangeRates.class, params);
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        return exchangeRates;
    }

    public ExchangeRates getHistorical() {
        ExchangeRates historicalExchangeRates = new ExchangeRates();

        Map<String, String> params = Collections.singletonMap("category", "historical");
        params.put("key", API_KEY);

        try {
            historicalExchangeRates = restTemplate.getForObject(baseUrl, ExchangeRates.class, params);
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return historicalExchangeRates;
    }
}
