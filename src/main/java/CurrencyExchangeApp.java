import model.ExchangeRates;
import services.ExchangeRatesService;

import java.math.BigDecimal;
import java.util.Locale;
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

        boolean validInput = false;
        BigDecimal initialAmount = BigDecimal.ZERO;

        while (!validInput) {
            try {
                initialAmount = new BigDecimal(userInput.nextLine());
                validInput = true;
            } catch (NumberFormatException nfe) {
                System.out.println("\nWhoops, something went wrong. Either that's not a number or it's not in the correct format.");
                System.out.print("Remember no dollar sign, and use this format (10.50). Try again: ");
            }
        }

        System.out.println("\nCalculating...");

        ExchangeRates exchangeRates = new ExchangeRates();

        System.out.println("\nWhat type of currency are you converting this to?");
        System.out.println("\nOnly enter that currency's three letter identification symbol.");
        System.out.print("If you need a list of acceptable currency types and their identification symbol, please enter 'L': ");

        String conversionCurrencyType = "";

        do {
            conversionCurrencyType = userInput.nextLine();

            if (conversionCurrencyType.equalsIgnoreCase("L")) {
                System.out.println("\nList of exchange rates:\n");
                System.out.println(exchangeRates.toString());
                System.out.println(spacer);
                System.out.print("\nWhat type of currency are you converting this to?: ");
            } else if (!exchangeRates.getRates().containsKey(conversionCurrencyType.toUpperCase(Locale.ROOT))) {
                System.out.println("\nPlease only enter that currency's three letter identification symbol.");
                System.out.println("If you need a list of acceptable currency types and their identification symbol, please enter 'L'\n");
                System.out.print("What are type of currency are you converting this to?: ");
            }
        } while (!exchangeRates.getRates().containsKey(conversionCurrencyType.toUpperCase(Locale.ROOT)));

        BigDecimal finalAmount = calculateCurrency(exchangeRates.getRates(), initialAmount, (conversionCurrencyType.toUpperCase(Locale.ROOT)));

        System.out.println("\n" + spacer);
        System.out.println(String.format("$%.2f in %s is %.2f", initialAmount, conversionCurrencyType.toUpperCase(Locale.ROOT), finalAmount));
        System.out.println(spacer);

        userInput.close();
    }

    public static BigDecimal calculateCurrency(Map<String, Double> currencyRates, BigDecimal amount, String currencySymbol) {
        BigDecimal conversionRate = BigDecimal.valueOf(currencyRates.get(currencySymbol));
        BigDecimal finalAmount = amount.multiply(conversionRate);
        return finalAmount;
    }
}