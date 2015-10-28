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
                        int newRow = row;
                        int newCol = col;
                        switch (matrix[row][col]) {
                            case UP: newRow--; break;
                            case RIGHT: newCol++; break;
                            case DOWN: newRow++; break;
                            case LEFT: newCol--; break;
                        }

                        if (newRow >= 0 && newCol >= 0 && newRow < matrix.length && newCol < matrix[0].length && matrix[newRow][newCol] == BLANK) {
                            matrix[newRow][newCol] = matrix[row][col];
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
}