package model;

import java.util.Map;

public class ExchangeRates {

    private Map<String, Double> rates;

    public ExchangeRates() {
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

}
