package services;

import model.ExchangeRates;
import org.springframework.web.client.RestTemplate;

public class ExchangeRatesService {
    private RestTemplate restTemplate = new RestTemplate();
    private final String API_BASE_URL = "https://api.currencybeacon.com/v1/";
    private final String API_KEY = "?api_key=bb017cfe3c7311e2f3c774f8af75c55a";

    public ExchangeRates getLatest() {
        return restTemplate.getForObject(API_BASE_URL + "latest" + API_KEY, ExchangeRates.class);
    }

    public ExchangeRates getHistorical() {
        return restTemplate.getForObject(API_BASE_URL + "historical" + API_KEY, ExchangeRates.class);
    }
}
