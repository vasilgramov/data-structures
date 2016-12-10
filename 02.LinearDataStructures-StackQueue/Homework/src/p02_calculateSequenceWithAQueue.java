import java.util.LinkedList;
import java.util.Scanner;

public class p02_calculateSequenceWithAQueue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int startNumber = Integer.parseInt(in.nextLine());

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(startNumber);

        for (int i = 0; i < 50; i++) {
            int currentNumber = queue.pop();
            System.out.println(currentNumber);

            queue.add(currentNumber + 1);
            queue.add(2 * currentNumber + 1);
            queue.add(currentNumber + 2);
        }
    }
}
