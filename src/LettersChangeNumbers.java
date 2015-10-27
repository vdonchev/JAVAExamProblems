import java.util.Scanner;

public class LettersChangeNumbers {
    public static void main(String[] args) {
        String[] items = new Scanner(System.in).nextLine().split("\\s+");
        double sum = 0;
        for (String item : items) {
            char firstLetter = item.charAt(0);
            char lastLetter = item.charAt(item.length() - 1);
            double num = Double.parseDouble(item.substring(1, item.length() - 1));
            num = Character.isUpperCase(firstLetter) ? num / toAlphabetPos(firstLetter) : num * toAlphabetPos(firstLetter);
            num = Character.isUpperCase(lastLetter) ? num - toAlphabetPos(lastLetter) : num + toAlphabetPos(lastLetter);
            sum += num;
        }

        System.out.printf("%.2f", sum);
    }

    private static int toAlphabetPos(char ch) {
        return Character.isUpperCase(ch) ? ch - ('A' - 1) : ch - ('a' - 1);
    }
}