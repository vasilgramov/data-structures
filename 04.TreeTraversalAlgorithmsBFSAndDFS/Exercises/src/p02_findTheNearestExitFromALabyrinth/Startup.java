package p02_findTheNearestExitFromALabyrinth;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Startup
{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int width = Integer.parseInt(in.nextLine());
        int height = Integer.parseInt(in.nextLine());

        int playerX = -1;
        int playerY = -1;

        char[][] labyrith = new char[height][width];
        for (int row = 0; row < height; row++) {
            char[] currentLine = in.nextLine().toCharArray();
            for (int col = 0; col < width; col++) {
                if (currentLine[col] == 's') {
                    playerX = row;
                    playerY = col;
                }

                labyrith[row][col] = currentLine[col];
            }
        }

        if (playerX == -1 || playerY == -1) {
            System.out.println("No start position!");
            return;
        }

        Point playerPosition = new Point(playerX, playerY);

        String shortestPathToExit = findShortestPathToExit(playerPosition, width, height, labyrith);
        System.out.println(shortestPathToExit);
    }

    private static String findShortestPathToExit(Point startPosition, int width, int height, char[][] labyrith) {
        LinkedList<Point> queue = new LinkedList<>();

        queue.add(startPosition);

        while (queue.size() > 0) {
            Point currentPoint = queue.poll();

            if (isExit(currentPoint, width, height)) {
                return tracePathBack(currentPoint);
            }

            tryDirection(queue, currentPoint, "U", 0, -1, width, height, labyrith);
            tryDirection(queue, currentPoint, "R", 1, 0, width, height, labyrith);
            tryDirection(queue, currentPoint, "D", 0, 1, width, height, labyrith);
            tryDirection(queue, currentPoint, "L", -1, 0, width, height, labyrith);

        }

        return "There is no exit!";
    }

    private static boolean isExit(Point currentCell, int width, int height) {
        boolean isOnBoardX = currentCell.getRow() == 0 || currentCell.getRow() == height - 1;
        boolean isOnBoardY = currentCell.getCol() == 0 || currentCell.getCol() == width - 1;

        return isOnBoardX || isOnBoardY;
    }

    private static void tryDirection(Queue<Point> queue, Point currentCell, String direction, int deltaX, int deltaY,
                                    int width, int height, char[][] labyrinth) {
        int newRow = currentCell.getRow() + deltaY;
        int newCol = currentCell.getCol() + deltaX;


        if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width && labyrinth[newRow][newCol] == '-') {
            labyrinth[newRow][newCol] = 's';

            Point nextCell = new Point(newRow, newCol);
            nextCell.setDirection(direction);
            nextCell.setPrevPoint(currentCell);
            queue.add(nextCell);
        }
    }

    private static String tracePathBack(Point currentCell) {
        if (currentCell.getPrevPoint() == null) {
            return "We are at the exit!";
        }

        StringBuilder path = new StringBuilder();
        while (currentCell.getPrevPoint() != null) {
            path.append(currentCell.getDirection());
            currentCell = currentCell.getPrevPoint();
        }

        StringBuilder reversedPath = new StringBuilder(path.capacity());
        for (int i = path.length() - 1; i >= 0 ; i--) {
            reversedPath.append(path.charAt(i));
        }

        return reversedPath.toString();
    }
}
