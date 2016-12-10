import java.util.*;

public class Sequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int startNumber = Integer.parseInt(in.nextLine());
        int searchedNumber = Integer.parseInt(in.nextLine());

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(startNumber);

        int numberToWork =  queue.pop();

        int counter = 1;

        while (searchedNumber >= startNumber) {
            if (searchedNumber == startNumber) {
                System.out.println(1);
                return;
            }

            counter++;
            if (numberToWork + 1 == searchedNumber) {
                System.out.println(counter);
                return;
            }
            queue.add(numberToWork + 1);
            counter++;
            if (numberToWork * 2 == searchedNumber) {
                System.out.println(counter);
                return;
            }
            queue.add(numberToWork * 2);
            numberToWork = queue.pop();
        }

        System.out.println("not reachable");
    }
}
