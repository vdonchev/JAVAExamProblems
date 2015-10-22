import java.util.Scanner;

public class MirrorNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;

        int numCount = scanner.nextInt();
        scanner.nextLine();

        String[] nums = new String[numCount];
        for (int i = 0; i < numCount; i++) {
            nums[i] = scanner.next();
        }

        for (int i = 0; i < numCount - 1; i++) {
            String currentString = nums[i];

            for (int j = (i + 1); j < numCount; j++) {
                String nextString = nums[j];
                String reversed = new StringBuilder(nextString).reverse().toString();
                if(nums[i].equals(reversed)) {
                    System.out.printf("%s<!>%s\n", currentString, nextString);
                    found = true;
                }
            }
        }

        if(!found) {
            System.out.println("No");
        }
    }
}