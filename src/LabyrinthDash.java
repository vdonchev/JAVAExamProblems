import java.util.Scanner;

public class LabyrinthDash {
    static char[][] labyrinth;
    static int lives = 3;
    static int movesMade = 0;
    static int row = 0;
    static int col = 0;
    static boolean dead;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int labyrinthHeight = scanner.nextInt();
        scanner.nextLine();

        labyrinth = new char[labyrinthHeight][];
        for (int i = 0; i < labyrinthHeight; i++) {
            labyrinth[i] = scanner.nextLine().toCharArray();
        }

        String moves = scanner.nextLine();

        dead = false;
        for (int i = 0; i < moves.length(); i++) {

            movesMade++;

            int newCol = col;
            int newRow = row;
            if (moves.charAt(i) == '>') {
                newCol++;
            } else if (moves.charAt(i) == '<') {
                newCol--;
            } else if (moves.charAt(i) == '^') {
                newRow--;
            } else {
                newRow++;
            }

            if (newCol < 0 || newCol >= labyrinth[newRow].length || newRow < 0 || newRow >= labyrinthHeight) {
                dead = true;
            }

            if (doMove(newCol, newRow)) break;
        }

        System.out.printf("Total moves made: %d", movesMade);
    }

    private static boolean doMove(int newCol, int newRow) {
        if (dead) {
            System.out.println("Fell off a cliff! Game Over!");
            return true;
        } else {

            if (labyrinth[newRow][newCol] == '_' ||
                    labyrinth[newRow][newCol] == '|') {
                movesMade--;
                System.out.println("Bumped a wall.");
            } else if (labyrinth[newRow][newCol] == '@' ||
                    labyrinth[newRow][newCol] == '#' ||
                    labyrinth[newRow][newCol] == '*') {
                row = newRow;
                col = newCol;
                lives--;
                System.out.printf("Ouch! That hurt! Lives left: %d\n", lives);
                if (lives <= 0) {
                    System.out.println("No lives left! Game Over!");
                    return true;
                }
            } else if(labyrinth[newRow][newCol] == '$') {
                row = newRow;
                col = newCol;
                lives++;
                System.out.printf("Awesome! Lives left: %d\n", lives);
                labyrinth[row][col] = '.';
            } else if(labyrinth[newRow][newCol] == ' ') {
                System.out.println("Fell off a cliff! Game Over!");
                return true;
            } else {
                row = newRow;
                col = newCol;
                System.out.println("Made a move!");
            }
        }
        return false;
    }
}