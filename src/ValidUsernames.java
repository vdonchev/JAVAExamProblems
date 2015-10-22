import java.util.ArrayList;
import java.util.Scanner;

public class ValidUsernames {
    public static void main(String[] args) {
        Scanner line = new Scanner(System.in);

        String[] allUsernames = line.nextLine().split("[\\s/()\\\\]");
        ArrayList<String> validUsername = new ArrayList<>();

        for (String allUsername : allUsernames) {
            if (allUsername.matches("[a-zA-Z][a-zA-Z0-9_]{2,25}")) {
                validUsername.add(allUsername);
            }
        }

        int bestLength = Integer.MIN_VALUE;
        int startIndex = 0;
        for (int i = 0; i < validUsername.size() - 1; i++) {
            int currentLength = validUsername.get(i).length() + validUsername.get(i + 1).length();
            if(currentLength > bestLength) {
                bestLength = currentLength;
                startIndex = i;
            }
        }

        System.out.println(validUsername.get(startIndex));
        System.out.println(validUsername.get(startIndex + 1));
    }
}