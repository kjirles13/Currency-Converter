import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class API {

    // Access Currency Beacon API
    public static Map<String, Double> accessAPI() {

        StringBuilder information = new StringBuilder();
        Map<String, Double> currencyRates = new HashMap<String, Double>();

        // Check connection and get currency conversion rates
        try {
            URL url = new URL("https://api.currencybeacon.com/v1/latest?api_key=bb017cfe3c7311e2f3c774f8af75c55a");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            // Check for successful response code
            if (!(responseCode == 200)) {
                throw new RuntimeException("Connection unsuccessful");

            } else {

                Scanner scanner = new Scanner(url.openStream());

                // Append incoming info to StringBuilder
                while (scanner.hasNext()) {
                    information.append(scanner.nextLine());
                }

                scanner.close();
            }

        } catch (IOException e) {
            System.out.println("IO Exception: Connection to API unsuccessful.");
        }

        String infoFullString = information.toString();

        // Get substring of useful information from information stream
        String infoSubString = infoFullString.substring((infoFullString.indexOf("ADA") - 1), infoFullString.indexOf("}}}"));
        String[] infoArray = infoSubString.split(",");

        // Put values in new Map<>
        for (String rate : infoArray) {
            String[] subArray = rate.split(":");
            String key = subArray[0].replace("\"", "");
            currencyRates.put(key, Double.valueOf(subArray[1]));
        }

        return currencyRates;
    }
}
