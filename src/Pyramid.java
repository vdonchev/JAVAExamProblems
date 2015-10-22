import java.util.Scanner;

public class Pyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfRows = scanner.nextInt();
        int topNum = scanner.nextInt();
        StringBuilder res = new StringBuilder();
        res.append(topNum);
        scanner.nextLine();

        for (int row = 1; row < numOfRows; row++) {
            String[] nums = scanner.nextLine().trim().split("\\s+");

            int currentTopNum = Integer.MAX_VALUE;
            boolean change = false;

            for (int num = 0; num < nums.length; num++) {
                int currentNum = Integer.parseInt(nums[num]);
                if(currentNum > topNum && currentNum < currentTopNum) {
                    currentTopNum  = currentNum;
                    change = true;
                }
            }

            if(change) {
                topNum = currentTopNum;
                res.append(", " + currentTopNum);
            } else {
                topNum++;
            }
        }

        System.out.println(res);
    }
}