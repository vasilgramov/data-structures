package p02_roundDance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Startup {
    public static int theDeepest = 1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numberOfEdges = Integer.parseInt(in.nextLine());
        int root = Integer.parseInt(in.nextLine());

        ArrayList<int[]> nodesEdges = new ArrayList<>();

        Tree tree = new Tree(root, 1);

        for (int i = 0; i < numberOfEdges; i++) {
            String[] inputData = in.nextLine().split("\\s+");
            int firstNode = Integer.parseInt(inputData[0]);
            int secondNode = Integer.parseInt(inputData[1]);

            if (tree.getValue() == firstNode) {
                tree.addChild(new Tree(secondNode, tree.getDepth() + 1));
            } else if (tree.getValue() == secondNode) {
                tree.addChild(new Tree(firstNode, tree.getDepth() + 1));
            } else {
                nodesEdges.add(new int[] { firstNode, secondNode });
            }
        }

        if (tree.getChildren().size() > 0) {
            theDeepest++;
        }

        outerloop:
        while (true) {
            for (int i = 0; i < nodesEdges.size(); i++) {
                if (nodesEdges.get(i) == null) {
                    break outerloop;
                }

                int firstNode = nodesEdges.get(i)[0];
                int secondNode = nodesEdges.get(i)[1];

                boolean hasFound = BFS(tree, firstNode, secondNode);
                if (hasFound) {
                    nodesEdges.set(i, null);
                }
            }
        }

        tree.print(0);
        System.out.println(theDeepest);
    }

    private static boolean BFS(Tree currentRoot, int firstNode, int secondNode ) {
        LinkedList<Tree> queue = new LinkedList<>();
        queue.add(currentRoot);

        while (queue.size() > 0) {
            Tree searchedTree = queue.poll();

            for (Tree child : searchedTree.getChildren()) {
                if (child.getValue() == firstNode) {
                    child.addChild(new Tree(secondNode, child.getDepth() + 1));
                    if (child.getDepth() + 1 > theDeepest) {
                        theDeepest = child.getDepth() + 1;
                    }
                    return true;
                } else if (child.getValue() == secondNode){
                    child.addChild(new Tree(firstNode, child.getDepth() + 1));
                    if (child.getDepth() + 1 > theDeepest) {
                        theDeepest = child.getDepth() + 1;
                    }
                    return true;
                } else {
                    queue.add(child);
                }
            }
        }

        return false;
    }
}
