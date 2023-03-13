package model;

import java.time.LocalDate;
import java.util.Map;

public class ExchangeRates {

    private int code;
    private LocalDate date;
    private String base;
    private Map<String, Double> rates;

    public ExchangeRates() {
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    //JSON object class
    // .getJSONArray
}
