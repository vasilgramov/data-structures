package p03_rideTheHorse;

import java.util.LinkedList;
import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int rows = Integer.parseInt(in.nextLine());
        int cols = Integer.parseInt(in.nextLine());

        int startRow = Integer.parseInt(in.nextLine());
        int startCol = Integer.parseInt(in.nextLine());

        int[][] matrix = new int[rows][cols];
        matrix[startRow][startCol] = 1;

        //printMatrix(matrix, rows, cols);

        Cell startPoint = new Cell(startRow, startCol, 1);
        LinkedList<Cell> queue = new LinkedList<>();
        queue.add(startPoint);

        BFS(matrix, queue, startPoint, rows, cols);

        printMatrix(matrix, rows, cols);
    }

    private static void BFS(int[][] matrix, LinkedList<Cell> queue, Cell startingPoint, int rows, int cols) {
        while (queue.size() > 0) {
            Cell currentCell = queue.poll();

            explore(matrix, queue, currentCell, -1, -2, rows, cols);     // up-left 01
            explore(matrix, queue, currentCell, -2, -1, rows, cols);     // up-left 02

            explore(matrix, queue, currentCell, -2, 1, rows, cols);      // up-right 01
            explore(matrix, queue, currentCell, -1, 2, rows, cols);      // up-right 02

            explore(matrix, queue, currentCell, 1, 2, rows, cols);       // down-right 01
            explore(matrix, queue, currentCell, 2 , 1, rows, cols);      // down-right 02

            explore(matrix, queue, currentCell, 1, -2, rows, cols);      // down-left 01
            explore(matrix, queue, currentCell, 2, -1, rows, cols);      // down-left 02
        }
    }

    private static void explore(int[][] matrix, LinkedList<Cell> queue, Cell currentCell, int deltaRow, int deltaCol, int totalRows, int totalCols) {
        if (currentCell.getRow() + deltaRow >= 0 && currentCell.getRow() + deltaRow < totalRows &&
                currentCell.getCol() + deltaCol >= 0 && currentCell.getCol() + deltaCol < totalCols &&
                matrix[currentCell.getRow() + deltaRow][currentCell.getCol() + deltaCol] == 0) {
            matrix[currentCell.getRow() + deltaRow][currentCell.getCol() + deltaCol] = currentCell.getValue() + 1;
            Cell newCell = new Cell(currentCell.getRow() + deltaRow, currentCell.getCol() + deltaCol, currentCell.getValue() + 1);
            queue.add(newCell);
        }
    }

    private static void printMatrix(int[][] matrix, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
