import java.util.Scanner;
import java.util.stream.Stream;

public class LongestOddEvenSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Stream.of(scanner
                .nextLine()
                .replace("(", " ")
                .replace(")", " ")
                .trim()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int longestSequnce = 0;
        for (int oddEven = 0; oddEven < 2; oddEven++) {
            for (int num = 0; num < nums.length; num++) {
                int rule = oddEven;
                int subCounter = 0;
                for (int i = num; i < nums.length; i++) {
                    if(Math.abs(nums[i]) % 2 == rule || nums[i] == 0) {
                        subCounter++;
                        rule = (rule + 1) % 2;
                    } else {
                        break;
                    }
                }

                if (subCounter > longestSequnce) {
                    longestSequnce = subCounter;
                }
            }
        }

        System.out.println(longestSequnce);
    }
}