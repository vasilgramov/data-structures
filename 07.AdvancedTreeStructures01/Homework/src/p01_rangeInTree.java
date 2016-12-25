import p01_AVLTreeImplementation.AVLTree;
import p01_AVLTreeImplementation.Node;

import java.util.ArrayList;
import java.util.Scanner;

public class p01_rangeInTree {
    public static ArrayList<Integer> nodesInRange = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        AVLTree<Integer> avlTree = new AVLTree<>();

        String[] nodes = in.nextLine().split("\\s+");
        for (int i = 0; i < nodes.length; i++) {
            avlTree.add(Integer.parseInt(nodes[i]));
        }

        String[] intervals = in.nextLine().split("\\s+");
        int from = Integer.parseInt(intervals[0]);
        int to = Integer.parseInt(intervals[1]);

        nodesInInterval(avlTree.getRoot(), from, to);

        for (int i = 0; i < nodesInRange.size(); i++) {
            System.out.println(nodesInRange.get(i));
        }
    }

    private static void nodesInInterval(Node<Integer> node, int from, int to) {
        if (node.getLeftChild() != null && node.compareTo(from) >= 0) {
            nodesInInterval(node.getLeftChild(), from, to);
        }

        if (node.getValue() >= from && node.getValue() <= to) {
            nodesInRange.add(node.getValue());
        }

        if (node.getRightChild() != null && node.compareTo(to) <= 0) {
            nodesInInterval(node.getRightChild(), from, to);
        }
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


