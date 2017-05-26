import java.util.*;

/**
 * Created by vladix on 5/25/17.
 */
public class p02_calculateSequence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        Deque<Integer> queue = new LinkedList<>();
        queue.addLast(n);

        int[] result = new int[50];
        for (int i = 0; i < 50; i++) {
            int num = queue.removeFirst();

            result[i] = num;

            queue.addLast(num + 1);
            queue.addLast(2 * num + 1);
            queue.addLast(num + 2);
        }

        System.out.println(Arrays.toString(result).replace("[", "").replace("]", ""));
    }
}
