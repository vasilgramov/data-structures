import java.util.LinkedList;
import java.util.Scanner;

public class p01_reverseNumberWithAStack {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        LinkedList<Integer> stack = new LinkedList<>();
        //int n = Integer.parseInt(in.nextLine());
        String[] numbersAsString = in.nextLine().split("\\s+");
        for (int i = 0; i < numbersAsString.length; i++) {
            stack.push(Integer.parseInt(numbersAsString[i]));
        }

        StringBuilder builder = new StringBuilder();
        for (Integer el : stack) {
            builder.append(el + " ");
        }

        System.out.println(builder.toString().trim());
    }
}
