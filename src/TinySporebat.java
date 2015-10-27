import java.util.Scanner;

public class TinySporebat {

    private static final int SPOREBAT_PRICE = 30;
    private static final int HEALING_POINTS = 200;
    private static final char HEALING_LETTER = 'L';
    private static final int DEAD = 0;

    private static int health = 5800;
    private static int glowCaps = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command;
        while (!(command = scanner.nextLine()).equals("Sporeggar")) {
            int currentGlowCaps = Integer.parseInt(command.substring(command.length() - 1));

            for (int pos = 0; pos < command.length() - 1; pos++) {
                char enemy = command.charAt(pos);

                if(enemy == HEALING_LETTER) {
                    health += HEALING_POINTS;
                } else {
                    health -= enemy;

                    if(health <= DEAD) {
                        System.out.printf("Died. Glowcaps: %d\n", glowCaps);
                        return;
                    }
                }
            }

            glowCaps += currentGlowCaps;
        }

        System.out.printf("Health left: %d\n", health);

        if ((glowCaps -= SPOREBAT_PRICE) >= 0) {
            System.out.printf("Bought the sporebat. Glowcaps left: %d", glowCaps);
        } else {
            System.out.printf("Safe in Sporeggar, but another %d Glowcaps needed.", Math.abs(glowCaps));
        }
    }
}