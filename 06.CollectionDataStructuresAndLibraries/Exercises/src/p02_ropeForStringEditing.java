import java.util.InputMismatchException;
import java.util.Scanner;

public class p02_ropeForStringEditing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        StringBuilder text = new StringBuilder();
        String command = in.nextLine();
        while (!command.equals("exit")) {
            String[] commandData = command.split("\\s+");

            if (commandData[0].equals("insert")) {
                text.insert(0, commandData[1]);
                System.out.println("OK");
            } else if (commandData[0].equals("append")) {
                text.append(commandData[1]);
                System.out.println("OK");
            } else if (commandData[0].equals("delete")) {
                Integer startIndex = Integer.parseInt(commandData[1]);
                Integer endIndex = Integer.parseInt(commandData[2]);

                if (startIndex < 0 || startIndex >= text.length()  ||
                        endIndex < 0 || endIndex >= text.length()) {
                    System.out.println("ERROR");
                } else {
                    text.replace(startIndex, endIndex, "");
                    System.out.println("OK");
                }
            }

            command = in.nextLine();
        }

        System.out.println(text.toString());
    }
}
