import java.util.Scanner;

public class Enigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfLines = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numOfLines; i++) {
            String phrase = scanner.nextLine();
            StringBuilder decoded = new StringBuilder();

            int phaseLength = (int)phrase.chars()
                    .filter(value -> !Character.isWhitespace(value) && !Character.isDigit(value))
                    .count() / 2;

            for (int j = 0; j < phrase.length(); j++) {
                if(Character.isWhitespace(phrase.charAt(j)) || Character.isDigit(phrase.charAt(j))) {
                    decoded.append(phrase.charAt(j));
                } else {
                    char decodedChar = (char)((int)phrase.charAt(j) + phaseLength);
                    decoded.append(decodedChar);
                }
            }

            System.out.println(decoded);
        }
    }
}