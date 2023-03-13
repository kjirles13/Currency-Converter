import model.ExchangeRates;
import services.ExchangeRatesService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class CurrencyExchangeApp {

    public static void main(String[] args) {
        ExchangeRatesService exchangeRatesService = new ExchangeRatesService();

        String spacer = "====================================";

        Scanner userInput = new Scanner(System.in);

        System.out.printf("%s\n\tCurrency Exchange Calculator\n%s\n", spacer, spacer);
        System.out.println("\nWelcome to the currency exchange calculator!\n");
        System.out.print("Please enter an amount in USD without any dollar sign symbols: ");
        BigDecimal initialAmount;

        while (true) {
            try {
                initialAmount = new BigDecimal(userInput.nextLine()).setScale(2, RoundingMode.CEILING);
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("\nWhoops, something went wrong. Either that's not a number or it's not in the correct format.");
                System.out.print("Remember no dollar sign, and use this format (10.50). Try again: ");
            }
        }

        System.out.println("\nCalculating...");

        try {
            ExchangeRates response = exchangeRatesService.getLatest();
            Map<String, Double> exchangeRates = response.getResponse().getRates();

            System.out.println("\nWhat type of currency are you converting this to?");
            System.out.println("\nOnly enter that currency's three letter identification symbol.");
            System.out.print("If you need a list of acceptable currency types and their identification symbol, please enter 'L': ");

            String conversionCurrencyType = "";

            while(!exchangeRates.containsKey(conversionCurrencyType.toUpperCase())) {
                conversionCurrencyType = userInput.nextLine();

                if (conversionCurrencyType.equalsIgnoreCase("L")) {
                    System.out.printf("\n%s\nList of exchange rates:\n\n", spacer);
                    printRates(exchangeRates);
                    System.out.printf("%s\n\nWhat type of currency are you converting this to?: ", spacer);
                } else if (!exchangeRates.containsKey(conversionCurrencyType.toUpperCase())) {
                    System.out.println("\nPlease only enter that currency's three letter identification symbol.");
                    System.out.println("If you need a list of acceptable currency types and their identification symbol, please enter 'L'\n");
                    System.out.print("What are type of currency are you converting this to?: ");
                }
            }

            BigDecimal finalAmount = calculateCurrency(exchangeRates, initialAmount, conversionCurrencyType.toUpperCase()).setScale(2, RoundingMode.CEILING);

            System.out.printf(Locale.ENGLISH, "\n%s\n$%,.2f in %s is %,.2f\n%s\n", spacer, initialAmount, conversionCurrencyType.toUpperCase(), finalAmount, spacer);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        userInput.close();
    }

    public static BigDecimal calculateCurrency(Map<String, Double> currencyRates, BigDecimal amount, String currencySymbol) {
        BigDecimal conversionRate = BigDecimal.valueOf(currencyRates.get(currencySymbol));
        return amount.multiply(conversionRate);
    }

    public static void printRates(Map<String, Double> rates) {
        for (Map.Entry<String, Double> rate : rates.entrySet()) {
            System.out.printf("%s : %f\n", rate.getKey(), rate.getValue());
        }
    }
}