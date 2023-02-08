import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class CurrencyExchangeApp {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("============================");
        System.out.println("Currency Exchange Calculator");
        System.out.println("============================\n");
        System.out.println("Welcome to the currency exchange calculator!\n");
        System.out.print("Please enter an amount in USD without any dollar sign symbols: ");

        //Get initial currency value and check for number format exception
        boolean validInput = false;
        BigDecimal initialAmount = new BigDecimal("0");

        while (!validInput) {
            try {
                initialAmount = new BigDecimal(userInput.nextLine());
                validInput = true;
            } catch (NumberFormatException nfe) {
                System.out.println();
                System.out.println("Whoops, something went wrong. Either that's not a number or it's not in the correct format.");
                System.out.print("Remember no dollar sign, and use this format (10.50). Try again: ");
            }
        }

        System.out.println("Calculating...");

        // Access API and get currency rates
        Map<String, Double> currencyRates = API.accessAPI();

        System.out.println("\nWhat type of currency are you converting this to?");
        System.out.println("Please only enter that currency's three letter identification symbol.");
        System.out.print("If you need a list of acceptable currency types and their identification symbol, please enter 'list': ");

        String conversionCurrencyType = "";

        // Get currency type to convert
        do {
            conversionCurrencyType = userInput.nextLine();

            if (conversionCurrencyType.equalsIgnoreCase("list")) {
                System.out.println("\nList of acceptable currency inputs:\n");

                for (Map.Entry<String, Double> rate : currencyRates.entrySet()) {
                    System.out.println(String.format("%s : %s", rate.getKey(), rate.getValue()));
                }

                System.out.print("\nWhat type of currency are you converting this to?: ");

            } else if (!currencyRates.containsKey(conversionCurrencyType.toUpperCase(Locale.ROOT))) {
                System.out.println("\nPlease only enter that currency's three letter identification symbol.");
                System.out.println("If you need a list of acceptable currency types and their identification symbol, please enter 'list'\n");
                System.out.print("What are type of currency are you converting this to?: ");
            }
        } while (!currencyRates.containsKey(conversionCurrencyType.toUpperCase(Locale.ROOT)));

        System.out.println("\nCalculating...\n");

        // Call method to convert currency
        String finalAmount = Calculator.calculateCurrency((conversionCurrencyType.toUpperCase(Locale.ROOT)), initialAmount);

        System.out.println("============================");
        System.out.println(String.format("$%s in %s is %s", initialAmount, conversionCurrencyType.toUpperCase(Locale.ROOT), finalAmount));
        System.out.println("============================");

        userInput.close();
    }
    //Calculator method
}