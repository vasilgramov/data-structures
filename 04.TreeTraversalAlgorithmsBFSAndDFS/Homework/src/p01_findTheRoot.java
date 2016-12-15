import java.util.ArrayList;
import java.util.Scanner;

public class p01_findTheRoot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numberOfNodes = Integer.parseInt(in.nextLine());
        int numberOfEdges = Integer.parseInt(in.nextLine());

        boolean[] isChild = new boolean[numberOfNodes];

        for (int i = 0; i < numberOfEdges; i++) {
            String[] inputData = in.nextLine().split("\\s+");
            int child = Integer.parseInt(inputData[1]);
            isChild[child] = true;
        }

        ArrayList<Integer> roots = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            if (!isChild[i]) {
                roots.add(i);
            }
        }

        if (roots.size() == 0) {
            System.out.println("No root!");
        } else if (roots.size() > 1) {
            System.out.println("Multiple root nodes!");
        } else {
            System.out.println(roots.get(0));
        }
    }
}
