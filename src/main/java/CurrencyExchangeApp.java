import model.ExchangeRates;
import services.ExchangeRatesService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class CurrencyExchangeApp {

    public static void main(String[] args) {
        ExchangeRatesService exchangeRatesService = new ExchangeRatesService();

        String spacer = "============================";

        Scanner userInput = new Scanner(System.in);

        System.out.println(spacer);
        System.out.println("Currency Exchange Calculator");
        System.out.println(spacer + "\n");
        System.out.println("Welcome to the currency exchange calculator!\n");
        System.out.print("Please enter an amount in USD without any dollar sign symbols: ");
        BigDecimal initialAmount = BigDecimal.ZERO;

        while (true) {
            try {
                initialAmount = new BigDecimal(userInput.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("\nWhoops, something went wrong. Either that's not a number or it's not in the correct format.");
                System.out.print("Remember no dollar sign, and use this format (10.50). Try again: ");
            }
        }

        System.out.println("\nCalculating...");
        ExchangeRates response = null;

        try {
            response = exchangeRatesService.getLatest();
            Map<String, Double> exchangeRates = response.getResponse().getRates();

            System.out.println("\nWhat type of currency are you converting this to?");
            System.out.println("\nOnly enter that currency's three letter identification symbol.");
            System.out.print("If you need a list of acceptable currency types and their identification symbol, please enter 'L': ");

            String conversionCurrencyType;

            do {
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
            } while (!exchangeRates.containsKey(conversionCurrencyType.toUpperCase()));

            BigDecimal finalAmount = calculateCurrency(exchangeRates, initialAmount, (conversionCurrencyType.toUpperCase()));

            System.out.printf("\n%s\n$%.2f in %s is %.2f\n%s", spacer, initialAmount, conversionCurrencyType.toUpperCase(), finalAmount, spacer);

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