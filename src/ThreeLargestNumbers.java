import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ThreeLargestNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        List<BigDecimal> nums = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            nums.add(new BigDecimal(scanner.nextLine()));
        }

        nums = nums.stream().sorted().collect(Collectors.toList());
        int indexator = 0;
        for (int i = nums.size() - 1; i >= 0; i--) {
            if(indexator++ >= 3) break;
            System.out.println(nums.get(i).toPlainString());
        }
    }
}