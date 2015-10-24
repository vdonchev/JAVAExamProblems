import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class OfficeStuff {

    static TreeMap<String, LinkedHashMap<String, Integer>> orders;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numOfOrders = scanner.nextInt();
        scanner.nextLine();

        orders = new TreeMap<>();

        for (int i = 0; i < numOfOrders; i++) {
            String inputOrder = scanner.nextLine();

            String[] tokens = inputOrder
                    .substring(1)
                    .substring(0, inputOrder.length() - 2)
                    .split(" - ");

            String companyName = tokens[0];
            String productName = tokens[2];
            int productAmount = Integer.parseInt(tokens[1]);

            if (!orders.containsKey(companyName)) {
                orders.put(companyName, new LinkedHashMap<>());
            }

            if (!orders.get(companyName).containsKey(productName)) {
                orders.get(companyName).put(productName, 0);
            }

            int currentProductAmount = orders.get(companyName).get(productName);

            orders.get(companyName).put(productName, currentProductAmount + productAmount);
        }

        for (String company : orders.keySet()) {
            System.out.printf("%s: ", company);

            boolean firstInList = true;
            for (Map.Entry<String, Integer> product : orders.get(company).entrySet()) {
                if (firstInList) {
                    System.out.printf("%s-%d", product.getKey(), product.getValue());
                    firstInList = false;
                } else {
                    System.out.printf(", %s-%d", product.getKey(), product.getValue());
                }
            }

            System.out.println();
        }
    }
}