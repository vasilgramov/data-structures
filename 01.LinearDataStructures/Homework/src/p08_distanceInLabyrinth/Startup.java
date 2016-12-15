package p08_distanceInLabyrinth;

import java.util.LinkedList;
import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int cols = Integer.parseInt(in.nextLine());
        int rows = Integer.parseInt(in.nextLine());

        int startRow = 0;
        int startCol = 0;

        String[][] matrix = new String[rows][cols];
        for (int row = 0; row < rows; row++) {
            char[] currentRow = in.nextLine().toCharArray();
            for (int col = 0; col < cols; col++){
                if (currentRow[col] == 's') {
                    startRow = row;
                    startCol = col;
                }

                matrix[row][col] = Character.toString(currentRow[col]);
            }
        }

        Cell startCell = new Cell(startRow, startCol, 1);
        matrix[startRow][startCol] = "1";

        LinkedList<Cell> queue = new LinkedList<>();
        queue.add(startCell);

        exploreMatrix(queue, matrix, rows, cols);

        printMatrix(matrix, rows, cols);
    }


    private static void exploreMatrix(LinkedList<Cell> queue, String[][] matrix, int totalRows, int totalCols) {
        while (queue.size() > 0) {
            Cell currentCell = queue.poll();

            explore(queue, currentCell, matrix, -1, 0, totalRows, totalCols);    // up
            explore(queue, currentCell, matrix, 0, 1, totalRows, totalCols);     // right
            explore(queue, currentCell, matrix, 1, 0, totalRows, totalCols);     // down
            explore(queue, currentCell, matrix, 0, -1, totalRows, totalCols);    // left
        }
    }

    private static void explore(LinkedList<Cell> queue, Cell currentCell, String[][] matrix, int deltaRow, int deltaCol, int totalRows, int totalCols) {
        if ((currentCell.getRow() + deltaRow >= 0 && currentCell.getRow() + deltaRow < totalRows) &&
                (currentCell.getCol() + deltaCol >= 0 && currentCell.getCol() + deltaCol < totalCols) &&
                (matrix[currentCell.getRow() + deltaRow][currentCell.getCol() + deltaCol].equals("-"))) {
            matrix[currentCell.getRow() + deltaRow][currentCell.getCol() + deltaCol] = (Integer.toString(currentCell.getValue() + 1));
            Cell newCell = new Cell(currentCell.getRow() + deltaRow, currentCell.getCol() + deltaCol, currentCell.getValue() + 1);
            queue.add(newCell);
        }
    }

    private static void printMatrix(String[][] matrix, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
