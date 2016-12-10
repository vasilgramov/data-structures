import java.util.LinkedList;
import java.util.Scanner;

public class p05_sequenceOfNM {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] inputData = in.nextLine().split("\\s+");

        int n = Integer.parseInt(inputData[0]);
        int m = Integer.parseInt(inputData[1]);

        if (n > m) {
            System.out.println("no solution");
            return;
        }

        LinkedList<Integer> jumps = new LinkedList<>();
        jumps.push(m);
        if (m % 2 != 0) {
            m--;
            jumps.push(m);
        }

        while (1 != 0) {
            if (m / 2 >= n) {
                m /= 2;
                jumps.push(m);
            } else if (m - 2 >= n) {
                m -=2;
                jumps.push(m);
            } else if (m - 1 >= n) {
                m -=1;
                jumps.push(m);
            }

            if (m == n) {
                break;
            }
        }

        for (Integer jump : jumps) {
            System.out.println(jump);
        }
    }
}
