import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class StraightFlush {

    static HashMap<String, String> nextCard;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cards = scanner.nextLine().split(",(\\s)+|(\\s)+|,");

        buildNextCardMap();

        boolean atLeastOneFlush = false;

        for (int card = 0; card < cards.length; card++) {

            String currentCard = cards[card];
            String nextCard = generateNextCard(currentCard);

            List<String> currentFlush = new ArrayList<>();
            currentFlush.add(currentCard);

            for (int step = 0; step < 4; step++) {
                boolean matchNextCard = false;

                if(nextCard.contains("N")) break;

                for (int next = 0; next < cards.length; next++) {
                    if(cards[next].equals(currentCard)) {
                        continue;
                    }

                    if(cards[next].equals(nextCard)) {
                        currentFlush.add(nextCard);
                        matchNextCard = true;

                        currentCard = nextCard;
                        nextCard = generateNextCard(nextCard);

                        break;
                    }
                }

                if(!matchNextCard) break;
            }

            if(currentFlush.size() == 5) {
                System.out.println(currentFlush);
                atLeastOneFlush = true;
            }
        }

        if(!atLeastOneFlush) {
            System.out.println("No Straight Flushes");
        }
    }

    private static String generateNextCard(String currentCard) {
        String cardFace = currentCard.substring(0, currentCard.length() - 1);
        String cardSuit = currentCard.substring(currentCard.length() - 1);

        return nextCard.get(cardFace) + cardSuit;
    }

    private static void buildNextCardMap() {
        nextCard = new HashMap<>();
        nextCard.put("2", "3");
        nextCard.put("3", "4");
        nextCard.put("4", "5");
        nextCard.put("5", "6");
        nextCard.put("6", "7");
        nextCard.put("7", "8");
        nextCard.put("8", "9");
        nextCard.put("9", "10");
        nextCard.put("10", "J");
        nextCard.put("J", "Q");
        nextCard.put("Q", "K");
        nextCard.put("K", "A");
        nextCard.put("A", "N");
    }
}