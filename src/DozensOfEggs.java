import java.util.Scanner;

public class DozensOfEggs {

    static final int numberOfDays = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int eggs = 0;

        for (int i = 0; i < numberOfDays; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            int count  = Integer.parseInt(tokens[0]);
            String type = tokens[1];

            if(type.equals("eggs")) {
                eggs += count;
            } else {
                eggs += (count * 12);
            }
        }

        System.out.printf("%d dozens + %d eggs", eggs / 12,eggs % 12);
    }
}