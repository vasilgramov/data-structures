import java.util.Arrays;
import java.util.Scanner;

public class p02_stringEditor {
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        String command = in.nextLine();
        while (!command.equals("END")) {
            String[] commandArgs = command.split("\\s+");
            String commandType = commandArgs[0];
            String[] commandParams = Arrays.copyOfRange(commandArgs, 1, commandArgs.length);

            executeCommand(commandType, commandParams);

            command = in.nextLine();
        }
    }

    private static void executeCommand(String commandType, String[] commandParams) {
        switch (commandType) {
            case "APPEND":
                result.append(commandParams[0]);
                System.out.println("OK");
                break;
            case "INSERT":
                int position = Integer.parseInt(commandParams[0]);
                String toInsert = commandParams[1];

                if (position < 0 || position >= commandParams.length) {
                    System.out.println("ERROR");
                } else {
                    result.insert(position, toInsert);
                    System.out.println("OK");
                }
                break;
            case "DELETE":
                int from = Integer.parseInt(commandParams[0]);
                int to = Integer.parseInt(commandParams[1]) + 1;
                if (from < 0 || from >= result.length() ||
                        to < 0 || to >= result.length() ||
                        from > to) {
                    System.out.println("ERROR");
                } else {
                    result.replace(from, to, "");
                    System.out.println("OK");
                }
                break;
            case "REPLACE":
                int fromToReplace = Integer.parseInt(commandParams[0]);
                int toToReplace = Integer.parseInt(commandParams[1]) + 1;

                if (fromToReplace < 0 || fromToReplace >= result.length() ||
                        toToReplace < 0 || toToReplace >= result.length() ||
                        fromToReplace > toToReplace) {
                    System.out.println("ERROR");
                } else {
                    result.replace(fromToReplace, toToReplace, commandParams[2]);
                    System.out.println("OK");
                }
                break;
            case "PRINT":
                System.out.println(result.toString());
                break;
        }
    }
}
