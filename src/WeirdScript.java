import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeirdScript {

    private static int END_OF_LOWERCASE_LETTERS = 26;
    private static int MAGIC_NUM = 52;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        char keyLetter = findKey(n);

        StringBuilder text = new StringBuilder();
        String command;
        while (!(command = scanner.nextLine()).equals("End")) {
            text.append(command);
        }

        Pattern pattern = Pattern.compile("([" + keyLetter + "]{2})(?<match>.{0,50}?)([" + keyLetter + "]{2})");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String output = matcher.group("match");
            if (!output.isEmpty()) {
                System.out.println(output);
            }
        }
    }

    private static char findKey(int n) {
        n %= MAGIC_NUM;
        if (n <= END_OF_LOWERCASE_LETTERS && n > 0) {
            return (char) (('a' - 1) + n);
        }

        n = ((n % END_OF_LOWERCASE_LETTERS) != 0) ? n % END_OF_LOWERCASE_LETTERS : 26;
        return (char) (('A' - 1) + n);
    }
}