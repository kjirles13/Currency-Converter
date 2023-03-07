package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ExchangeRates {

    @JsonProperty("rates")
    private Map<String, Double> rates;

    public ExchangeRates() {}
    

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        String currencyRates = "";

        for (Map.Entry<String, Double> rate : rates.entrySet()) {
            currencyRates = currencyRates + String.format("%s : %s\n", rate.getKey(), rate.getValue());
        }

        return currencyRates;
    }
}
