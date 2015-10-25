import java.util.Scanner;

public class MagicCard {
    static final String deck = ":)2345678910JQKA"; // Cool ah? ho-ho

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cards = scanner.nextLine().split(" ");
        String needleType = scanner.next();
        String magicCard = scanner.next();
        int allCardsSum = 0;
        int iteratorStart = (needleType.equals("even")) ? 0 : 1;
        for (int i = iteratorStart; i < cards.length; i += 2) {
            String currentCard = cards[i];
            int cardPower = deck.indexOf(currentCard.substring(0, currentCard.length() - 1)) * 10;
            if (currentCard.substring(currentCard.length() - 1).equals(magicCard.substring(magicCard.length() - 1))) {
                cardPower *= 2;
            }

            if (currentCard.substring(0, currentCard.length() - 1).equals(magicCard.substring(0, magicCard.length() - 1))) {
                cardPower *= 3;
            }

            allCardsSum += cardPower;
        }

        System.out.println(allCardsSum);
    }
}