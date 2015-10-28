import java.util.*;

public class Orders {
    static LinkedHashMap<String, TreeMap<String, Integer>> orders;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());

        orders = new LinkedHashMap<>();
        String[] order;
        for (int i = 0; i < count; i++) {
            order = scanner.nextLine().split("\\s+");

            String customer = order[0];
            int amount = Integer.parseInt(order[1]);
            String product = order[2];

            if(!orders.containsKey(product)) {
                orders.put(product, new TreeMap<>());
            }

            if(!orders.get(product).containsKey(customer)) {
                orders.get(product).put(customer, 0);
            }

            int currentAmount = orders.get(product).get(customer);
            orders.get(product).put(customer, currentAmount + amount);
        }

        for (String product : orders.keySet()) {
            System.out.printf("%s:", product);
            boolean first = true;
            for (Map.Entry<String, Integer> customer : orders.get(product).entrySet()) {
                String separator = ", ";
                if(first) {
                    separator = " ";
                    first = false;
                }

                System.out.printf("%s%s %d", separator, customer.getKey(), customer.getValue());
            }
            System.out.println();
        }
    }
}