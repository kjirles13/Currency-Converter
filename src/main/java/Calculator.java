import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

public class Calculator {

    public static String calculateCurrency(String currencySymbol, BigDecimal amount) {

        Map<String, Double> currencyRateMap = API.accessAPI();

        BigDecimal conversionRate = BigDecimal.valueOf(currencyRateMap.get(currencySymbol));

        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(amount.multiply(conversionRate));
    }
}
