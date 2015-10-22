import java.util.Scanner;

public class StuckNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfNums = scanner.nextInt();

        String[] nums = new String[numOfNums];
        for (int i = 0; i < numOfNums; i++) {
            nums[i] = scanner.next();
        }

        boolean match = false;
        for (int i = 0; i < nums.length; i++) {
            String num1 = nums[i];
            for (int j = 0; j < nums.length; j++) {
                String num2 = nums[j];
                for (int k = 0; k < nums.length; k++) {
                    String num3 = nums[k];
                    for (int l = 0; l < nums.length; l++) {
                        String num4 = nums[l];
                        if(num1 != num2 &&
                                num1 != num3 &&
                                num1 != num4 &&
                                num2 != num3 &&
                                num2 != num4 &&
                                num3 != num4) {
                            StringBuilder builder = new StringBuilder();
                            StringBuilder builder2 = new StringBuilder();
                            builder.append(num1).append(num2);
                            builder2.append(num3).append(num4);
                            if(builder.toString().equals(builder2.toString())) {
                                System.out.printf("%s|%s==%s|%s\n", num1, num2, num3, num4);
                                match = true;
                            }
                        }
                    }
                }
            }
        }

        if(!match) {
            System.out.println("No");
        }
    }
}
