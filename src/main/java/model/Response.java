package model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class Response {
    private Date date;
    private String base;
    private Map<String, Double> rates;

    public Response() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /* public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }*/

    @Override
    public String toString() {
        String exchangeRates = "";
        for (Map.Entry<String, Double> rate : rates.entrySet()) {
            exchangeRates = exchangeRates + rate.getKey() + " : " + rate.getValue() + "\n";
        }
        return exchangeRates;
    }
}
