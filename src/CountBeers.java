import java.util.Scanner;

public class CountBeers {
    static final int STACK_COUNT = 20;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalBeers = 0;
        String[] tokens;
        while (!(tokens = scanner.nextLine().split("\\s+"))[0].equals("End")) {
            int quantity = Integer.parseInt(tokens[0]);
            String type = tokens[1];

            totalBeers = (type.equals("stacks")) ? totalBeers + (quantity * STACK_COUNT) : totalBeers + quantity;
        }

        System.out.printf("%d stacks + %d beers", totalBeers / STACK_COUNT, totalBeers % STACK_COUNT);
    }
}