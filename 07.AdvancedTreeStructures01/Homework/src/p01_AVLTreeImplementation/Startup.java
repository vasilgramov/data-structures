package p01_AVLTreeImplementation;

import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        AVLTree<Integer> avlTree = new AVLTree<>();

        String[] elements = in.nextLine().split("\\s+");
        for (int i = 0; i < elements.length; i++) {
            avlTree.add(Integer.parseInt(elements[i]));
        }

        String[] intervals = in.nextLine().split("\\s+");
        int start = Integer.parseInt(intervals[0]);
        int end = Integer.parseInt(intervals[1]);


        print(avlTree.getRoot(), 1);
    }

    private static void print(Node<Integer> node, int indent) {
        System.out.println(newString(" ", indent) + node.getValue());

        if (node.getLeftChild() != null) {
            print(node.getLeftChild(), indent + 1);
        }

        if (node.getRightChild() != null) {
            print(node.getRightChild(), indent + 1);
        }
    }

    private static String newString(String str, int count) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            builder.append(str);
        }

        return builder.toString();
    }
}
