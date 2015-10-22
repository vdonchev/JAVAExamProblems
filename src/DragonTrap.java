import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DragonTrap {

    static List<int[]> circlePositions;
    static boolean[] directions;
    static ArrayList<Character> textToRotate;
    static int top;
    static int bottom;
    static int right;
    static int left;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int matrixRows = scanner.nextInt();
        scanner.nextLine();

        char[][] matrix = new char[matrixRows][];
        char[][] matrixClone = new char[matrixRows][];

        for (int i = 0; i < matrixRows; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();
            matrixClone[i] = matrix[i].clone();
        }

        int matrixCols = matrix[0].length;
        String command;
        while (!(command = scanner.nextLine()).equals("End")) {
            String[] tokens = command.substring(1).split("\\s+|\\)\\s+");

            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);
            int radius = Integer.parseInt(tokens[2]);
            int rotations = Integer.parseInt(tokens[3]);

            if (radius + col < 0 || radius + row < 0 || col - radius >= matrixCols || row - radius >= matrixRows) {
                continue;
            }

            directions = new boolean[4];
            CalculateArea(matrixRows, matrixCols, row, col, radius);
            textToRotate = new ArrayList<>();
            circlePositions = new ArrayList<>();
            ExtractChars(matrix);

            if (textToRotate.size() == 0) {
                continue;
            }

            RotateText(rotations);

            for (int i = 0; i < circlePositions.size(); i++) {
                int mRow = circlePositions.get(i)[0];
                int mCol = circlePositions.get(i)[1];
                matrix[mRow][mCol] = textToRotate.get(i);
            }
        }

        int changes = 0;
        for (int i = 0; i < matrixRows; i++) {
            for (int j = 0; j < matrixCols; j++) {
                System.out.print(matrix[i][j]);

                if(matrix[i][j] != matrixClone[i][j]) {
                    changes++;
                }
            }
            System.out.println();
        }

        System.out.printf("Symbols changed: %d", changes);
    }

    private static void RotateText(int rotations) {
        if (rotations < 0) {
            int numOfRotations = Math.abs(rotations) % textToRotate.size();
            for (int i = 0; i < numOfRotations; i++) {
                Character temp = textToRotate.get(0);
                textToRotate.add(temp);
                textToRotate.remove(0);
            }
        } else {
            int numOfRotations = rotations % textToRotate.size();
            for (int i = 0; i < numOfRotations; i++) {
                Character temp = textToRotate.get(textToRotate.size() - 1);
                textToRotate.add(0, temp);
                textToRotate.remove(textToRotate.size() - 1);
            }
        }
    }

    private static void ExtractChars(char[][] matrix) {
        if (directions[0]) {
            for (int colX = left; colX <= right; colX++) {
                textToRotate.add(matrix[top][colX]);
                circlePositions.add(new int[]{top, colX});
            }
            top++;
        }

        if (directions[1]) {
            for (int rowY = top; rowY <= bottom; rowY++) {
                textToRotate.add(matrix[rowY][right]);
                circlePositions.add(new int[]{rowY, right});
            }
            right--;
        }

        if (directions[2]) {
            for (int colX = right; colX >= left; colX--) {
                textToRotate.add(matrix[bottom][colX]);
                circlePositions.add(new int[]{bottom, colX});
            }
            bottom--;
        }

        if (directions[3]) {
            for (int rowY = bottom; rowY >= top; rowY--) {
                textToRotate.add(matrix[rowY][left]);
                circlePositions.add(new int[]{rowY, left});
            }
            left++;
        }
    }

    private static void CalculateArea(int matrixRows, int matrixCols, int row, int col, int radius) {
        if (row - radius < 0) {
            top = 0;
        } else {
            top = row - radius;
            directions[0] = true;
        }

        if (col + radius >= matrixCols) {
            right = matrixCols - 1;
        } else {
            right = col + radius;
            directions[1] = true;
        }

        if (row + radius >= matrixRows) {
            bottom = matrixRows - 1;
        } else {
            bottom = row + radius;
            directions[2] = true;
        }

        if (col - radius < 0) {
            left = 0;
        } else {
            left = col - radius;
            directions[3] = true;
        }
    }
}