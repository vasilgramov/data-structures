package p02_sweepAndPrune;

import java.util.ArrayList;
import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<Object> allObjects = new ArrayList<>();
        String command = in.nextLine();
        command = readInputData(in, allObjects, command);

        insertionSort(allObjects);

        int tickCount = 1;
        while (!command.equals("end")) {
            command = in.nextLine();

            moveObject(allObjects, command);

            checkForCollisions(allObjects, tickCount);

            tickCount++;
        }
    }

    private static String readInputData(Scanner in, ArrayList<Object> allObjects, String command) {
        while (!command.equals("start")) {
            String[] commandArgs = command.split("\\s+");
            String name = commandArgs[1];
            int x = Integer.parseInt(commandArgs[2]);
            int y = Integer.parseInt(commandArgs[3]);

            Object newObject = new Object(name, x, y);
            allObjects.add(newObject);

            command = in.nextLine();
        }
        return command;
    }

    private static void checkForCollisions(ArrayList<Object> allObjects, int tickCount) {
        for (int i = 0; i < allObjects.size(); i++) {
            Object toCheck = allObjects.get(i);
            for (int j = i + 1; j < allObjects.size(); j++) {
                Object current = allObjects.get(j);

                if (current.getX1() < toCheck.getX2()) {
                    if (collisionDetector(toCheck, current)) {
                        System.out.printf("(%d) %s collides with %s\r\n", tickCount, toCheck.getName(), current.getName());
                    }
                } else {
                    break;
                }
            }
        }
    }

    private static boolean collisionDetector(Object toCheck, Object current) {
        return Math.abs(toCheck.getY1() - current.getY1()) < 10;
    }

    private static void moveObject(ArrayList<Object> allObjects, String command) {
        if (!command.equals("tick")) {
            String[] commandArgs = command.split("\\s+");
            String name = commandArgs[1];
            int newX = Integer.parseInt(commandArgs[2]);
            int newY = Integer.parseInt(commandArgs[3]);

            for (Object allObject : allObjects) {
                if (allObject.getName().equals(name)) {
                    allObject.setX1(newX);
                    allObject.setY1(newY);
                    break;
                }
            }

            insertionSort(allObjects);
        }
    }

    private static void insertionSort(ArrayList<Object> objects) {
        for (int i = 1; i < objects.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (objects.get(j).getX1() < objects.get(j - 1).getX1()) {
                    Object temp = objects.get(j);
                    objects.set(j, objects.get(j - 1));
                    objects.set(j - 1, temp);
                }
            }
        }
    }
}
