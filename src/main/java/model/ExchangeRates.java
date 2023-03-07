package model;

import java.util.Map;

public class ExchangeRates {
    private Map<String, Double> rates;

    public ExchangeRates() {}

    public ExchangeRates(Map<String, Double> rates) {
        this.rates = rates;
    }

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
