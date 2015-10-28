import java.util.*;
import java.util.stream.Stream;

public class Biggest3PrimeNumbers {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] nums = Stream.of(scanner
                .nextLine()
                .replace("(", " ")
                .replace(")", " ")
                .trim()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .sorted()
                .distinct()
                .toArray();

        List<Integer> biggetPrimes = new ArrayList<>();
        for (int num = nums.length - 1; num >= 0; num--) {
            if (primeChecker(nums[num])) {
                biggetPrimes.add(nums[num]);
            }

            if(biggetPrimes.size() == 3) break;
        }

        if(biggetPrimes.size() >= 3) {
            System.out.println(biggetPrimes.stream()
                    .mapToInt(value -> value)
                    .sum());
        } else {
            System.out.println("No");
        }
    }

    private static boolean primeChecker(int num) {
        boolean prime = true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0 && i != num || num < 2) {
                prime = false;
            }
        }

        return prime && num > 1;
    }
}