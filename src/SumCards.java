import java.util.Scanner;

public class SumCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] cardFaces = scanner.nextLine().trim().split(" ");
        int[] cardValues = new int[cardFaces.length];

        for (int i = 0; i < cardFaces.length; i++) {
            cardValues[i] = getCardValue(cardFaces[i].substring(0, cardFaces[i].length() - 1));
        }

        int sumOfAllCards = 0;
        for (int i = 0; i < cardValues.length; i++) {
            int numOfEquals = 1;
            int currentCard = cardValues[i];
            int currentCardSum = currentCard;

            for (int j = i + 1; j < cardValues.length; j++) {
                int subCard = cardValues[j];
                if(subCard == currentCard) {
                    i++;
                    numOfEquals++;
                    currentCardSum += currentCard;
                } else {
                    break;
                }
            }

            if(numOfEquals > 1) {
                currentCardSum *= 2;
            }

            sumOfAllCards += currentCardSum;
        }

        System.out.println(sumOfAllCards);
    }

    private static int getCardValue(String card) {
        if(card.equals("J") || card.equals("Q") || card.equals("K") || card.equals("A")) {
            switch (card) {
                case "J": return 12;
                case "Q": return 13;
                case "K": return 14;
                case "A": return 15;
            }
        }

        return Integer.parseInt(card);
    }
}
