import java.util.Scanner;

public class AddingAngles {
    public static void main(String[] args) {
        int a = 2;
        String b = Integer.toString(a);
        System.out.println(b.getClass());

        Scanner scanner = new Scanner(System.in);

        int numOfNums = scanner.nextInt();
        int[] angles = new int[numOfNums];
        for (int i = 0; i < numOfNums; i++) {
            angles[i] = scanner.nextInt();
        }

        boolean match = false;
        for (int first = 0; first < numOfNums; first++) {
            for (int second = first + 1; second < numOfNums; second++) {
                for (int third = second + 1; third < numOfNums; third++) {
                    int angleA = angles[first];
                    int angleB = angles[second];
                    int angleC = angles[third];

                    int sum = (angleA + angleB + angleC);

                    if(sum % 360 == 0) {
                        match = true;
                        System.out.printf("%d + %d + %d = %d degrees\n", angleA, angleB, angleC, sum);

                    }
                }
            }
        }

        if(!match) {
            System.out.println("No");
        }
    }
}
