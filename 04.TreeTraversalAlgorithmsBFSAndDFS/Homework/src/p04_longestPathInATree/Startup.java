package p04_longestPathInATree;

import java.util.*;

public class Startup {

    public static LinkedList<Tree> queue = new LinkedList<>();

    public static ArrayList<Integer> firstPath;
    public static ArrayList<Integer> secondPath;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numberOfNodes = Integer.parseInt(in.nextLine());
        int numberOfEdges = Integer.parseInt(in.nextLine());

        HashMap<Integer, Integer> childByParent = new HashMap<>();
        HashSet<Integer> nodes = new HashSet<>();
        for (int i = 0; i < numberOfEdges; i++) {
            String[] lineData = in.nextLine().split("\\s+");
            int parent = Integer.parseInt(lineData[0]);
            int child = Integer.parseInt(lineData[1]);

            childByParent.put(child, parent);

            nodes.add(parent);
            nodes.add(child);
        }

        int rootValue = 0;
        for (Integer node : nodes) {
            if (!childByParent.containsKey(node)) {
                rootValue = node;
                break;
            }
        }

        Tree root = new Tree(rootValue);

        ArrayList<Tree> trees = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : childByParent.entrySet()) {
            builingTree(root, trees, entry);
        }

        int index = 0;
        while (trees.size() > 0) {
            boolean hasFound = DFS(root, trees.get(index));

            if (hasFound) {
                trees.remove(index);
                index = 0;
            } else {
                index++;
            }
        }

        ArrayList<Integer> currentPath = new ArrayList<>();
        currentPath.add(root.getValue());

        ArrayList<Integer> path01 = new ArrayList<>();
        ArrayList<Integer> path02 = new ArrayList<>();

        for (Tree child : root.getChildren()) {
            longestPath(child, currentPath);

            // TODO: refactor
            if (firstPath.size() > secondPath.size()) {
                if (path01.size() == 0) {
                    path01 = new ArrayList<>(firstPath);
                } else if (path02.size() == 0) {
                    path02 = new ArrayList<>(firstPath);
                } else if (firstPath.size() > path01.size()) {
                    path01 = new ArrayList<>(firstPath);
                } else if (firstPath.size() > path02.size()) {
                    path02 = new ArrayList<>(firstPath);
                }
            } else {
                if (path01.size() == 0) {
                    path01 = new ArrayList<>(secondPath);
                } else if (path02.size() == 0) {
                    path02 = new ArrayList<>(secondPath);
                } else if (secondPath.size() > path01.size()) {
                    path01 = new ArrayList<>(secondPath);
                } else if (secondPath.size() > path02.size()) {
                    path02 = new ArrayList<>(secondPath);
                }
            }
            // ......................................

            currentPath.clear();
            firstPath.clear();
            secondPath.clear();
        }

        printOutput(path01, path02);
    }

    private static void printOutput(ArrayList<Integer> path01, ArrayList<Integer> path02) {
        for (Integer item : path01) {
            System.out.print(item + " ");
        }

        System.out.println();

        for (Integer item : path02) {
            System.out.print(item + " ");
        }
    }

    private static void builingTree(Tree root, ArrayList<Tree> trees, Map.Entry<Integer, Integer> entry) {
        int child = entry.getKey();
        int parent = entry.getValue();

        if (parent == root.getValue()) {
            root.addChild(new Tree((child)));
        } else {
            boolean hasFound;

            queue.clear();
            queue.add(root);
            hasFound = BFS(parent, child);

            if (hasFound) {
                return;
            }

            for (Tree tree : trees) {
                queue.clear();
                queue.add(tree);
                hasFound = BFS(parent, child);
                if (hasFound) {
                    break;
                }
            }

            if (hasFound) {
                return;
            }

            Tree tree = new Tree(parent);
            tree.addChild(new Tree(child));
            trees.add(tree);
        }
    }

    private static boolean DFS(Tree root, Tree seachedTree) {
        if (root.getValue() == seachedTree.getValue()) {
            for (Tree tree : seachedTree.getChildren()) {
                root.addChild(tree);
            }

            return true;
        }
        else {
            for (Tree tree : root.getChildren()) {
                if (DFS(tree, seachedTree)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean BFS(int parentValue, int childValue) {
        while (queue.size() > 0) {
            Tree currentTree = queue.poll();
            if (currentTree.getValue() == parentValue) {
                currentTree.addChild(new Tree(childValue));
                return true;
            }

            for (Tree child : currentTree.getChildren()) {
                if (parentValue == child.getValue()) {
                    child.addChild(new Tree(childValue));
                    return true;
                }

                queue.add(child);
            }
        }

        return false;
    }

    private static void longestPath(Tree tree, ArrayList<Integer> currentPath) {
        currentPath.add(tree.getValue());

        if (tree.getChildren().size() > 0) {
            for (Tree child : tree.getChildren()) {
                longestPath(child, currentPath);
            }

            currentPath.remove(currentPath.size() - 1);
        } else {
            if (firstPath == null) {
                firstPath = new ArrayList<>(currentPath);
            } else if (secondPath == null) {
                secondPath = new ArrayList<>(currentPath);
            } else if (currentPath.size() > firstPath.size()) {
                firstPath = new ArrayList<>(currentPath);
            } else if (currentPath.size() > secondPath.size()) {
                secondPath = new ArrayList<>(currentPath);
            }

            currentPath.remove(currentPath.size() - 1);
        }
    }
}