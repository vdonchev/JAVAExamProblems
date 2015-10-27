import java.util.Scanner;

public class FireTheArrows {
    static char[][] matrix;
    static final char BLANK = 'o';
    static final char UP = '^';
    static final char RIGHT = '>';
    static final char DOWN = 'v';
    static final char LEFT = '<';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixRows = Integer.parseInt(scanner.nextLine());

        matrix = new char[matrixRows][];
        for (int i = 0; i < matrixRows; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }

        boolean moved = true;
        while (moved) {
            moved = false;
            for (int row = 0; row < matrixRows; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    if (matrix[row][col] != BLANK) {
                        if (moveArrow(matrix[row][col], row, col)) {
                            matrix[row][col] = BLANK;
                            moved = true;
                        }
                    }
                }
            }
        }

        printResult(matrixRows);
    }

    private static void printResult(int matrixRows) {
        for (int row = 0; row < matrixRows; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static boolean moveArrow(char arrow, int row, int col) {
        switch (arrow) {
            case UP: row--; break;
            case RIGHT: col++; break;
            case DOWN: row++; break;
            case LEFT: col--; break;
        }

        return canBeMoved(arrow, row, col);
    }

    private static boolean canBeMoved(char arrow, int row, int col) {
        if (row >= 0 && col >= 0 && row < matrix.length && col < matrix[0].length && matrix[row][col] == BLANK) {
            matrix[row][col] = arrow;
            return true;
        }

        return false;
    }
}