package p01_playWithTree;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

public class Startup {

    public static int maxDepth = 0;
    public static ArrayList<Integer> longestPath = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Map<Integer, Tree<Integer>> nodeByValue = new HashMap<>();

        int n = Integer.parseInt(in.nextLine());
        for (int i = 1; i < n; i++) {
            buildTree(in, nodeByValue);
        }

        int P = Integer.parseInt(in.nextLine());
        int S = Integer.parseInt(in.nextLine());

        Tree<Integer> root = getRoot(nodeByValue);
        System.out.println("ROOT: " + root.getValue());

        ArrayList<Integer> leafs = new ArrayList<>();
        getLeaf(root, leafs);

        Collections.sort(leafs);
        System.out.print("Leaf nodes: ");
        for (int i = 0; i < leafs.size(); i++) {
            System.out.print(leafs.get(i) + " ");
        }
        System.out.println();

        getDepth(root, 1);

        ArrayList<Integer> middleNodes = new ArrayList<>();
        int startPosition = 1;
        getMiddleNodes(middleNodes, root, startPosition);
        Collections.sort(middleNodes);
        System.out.print("Middle nodes: ");
        for (int i = 0; i < middleNodes.size(); i++) {
            System.out.print(middleNodes.get(i) + " ");
        }
        System.out.println();

        ArrayList<Integer> currentPath = new ArrayList<>();
        currentPath.add(root.getValue());
        getLongestPath(currentPath, root);
        System.out.print("Longest path: ");
        for (int i = 0; i < longestPath.size(); i++) {
            System.out.print(longestPath.get(i) + " ");
        }
        System.out.println();

        ArrayList<ArrayList<Integer>> pathsWithSumP = new ArrayList<>();
        currentPath.clear();
        currentPath.add(root.getValue());
        getPathsWithSumP(P, root, currentPath, pathsWithSumP);
        System.out.print("Paths with sum P: ");
        for (int i = 0; i < pathsWithSumP.size(); i++) {
            System.out.print(pathsWithSumP.get(i) + " ");
        }
        System.out.println();

        ArrayList<ArrayList<Integer>> subTreesWithSumS = new ArrayList<>();
        currentPath.clear();

    }

    public static void getDepth(Tree<Integer> root, int depth) {
        for (Tree<Integer> node : root.getChildren()) {
            if (node.getChildren() != null) {
                if (depth + 1 > maxDepth) {
                    maxDepth = depth + 1;
                }
                getDepth(node, depth + 1);
            }
        }
    }

    private static Tree<Integer> getRoot(Map<Integer, Tree<Integer>> nodeByValue) {
        Object root = new Object();
        for (Tree<Integer> firstAndLastElement : nodeByValue.values()) {
            root = firstAndLastElement;
            break;
        }
        return (Tree) root;
    }

    private static void buildTree(Scanner in, Map<Integer, Tree<Integer>> nodeByValue) {
        String[] currentLineData = in.nextLine().split("\\s+");
        int parent = Integer.parseInt(currentLineData[0]);
        int child = Integer.parseInt(currentLineData[1]);
        Tree<Integer> childAsNode = new Tree<>(child);

        if (nodeByValue.containsKey(parent)) {
            Tree<Integer> currentNode = nodeByValue.get(parent);
            childAsNode.setParentTo(parent);
            currentNode.addChild(childAsNode);
        } else {
            boolean hasFound = false;
            for (Tree<Integer> node : nodeByValue.values()) {
                hasFound = searchInDepth(parent, childAsNode, hasFound, node);
            }

            if (!hasFound) {
                for (Tree<Integer> parents : nodeByValue.values()) {
                    if (child == parents.getValue()) {
                        Tree<Integer> oldROOT = nodeByValue.get(parents.getValue());
                        oldROOT.setParentTo(parent);
                        nodeByValue.clear();
                        Tree<Integer> newROOT = new Tree<>(parent, oldROOT);
                        nodeByValue.put(newROOT.getValue(), newROOT);
                        hasFound = true;
                    }
                }
            }

            if (!hasFound) {
                Tree<Integer> newNode = new Tree<>(parent, childAsNode);
                nodeByValue.put(newNode.getValue(), newNode);
            }
        }
    }

    private static boolean searchInDepth(int parent, Tree<Integer> childAsNode, boolean hasFound, Tree<Integer> node) {
        for (Tree<Integer> searchedChild : node.getChildren()) {
            if (parent == searchedChild.getValue()) {
                childAsNode.setParentTo(parent);
                searchedChild.addChild(childAsNode);
                hasFound = true;
                return hasFound;
            } else if (searchedChild.getChildren() != null) {
                hasFound = searchInDepth(parent, childAsNode, hasFound, searchedChild);
                if (hasFound) {
                    return true;
                }
            }
        }

        return hasFound;
    }

    private static Integer getLeaf(Tree<Integer> root, ArrayList<Integer> leafs) {
        if (root.getChildren().size() > 0) {
            for (Tree<Integer> node : root.getChildren()) {
                getLeaf(node, leafs);
            }
        } else {
            //System.out.println(root.getValue());
            leafs.add(root.getValue());
        }

        return 0;
    }

    private static void getMiddleNodes(ArrayList<Integer> middleNodes, Tree<Integer> root, int starPosition) {
        for (Tree<Integer> node : root.getChildren()) {
            if (starPosition == maxDepth / 2 && node.getChildren().size() > 0) {
                middleNodes.add(node.getValue());
            } else {
                getMiddleNodes(middleNodes, node, starPosition + 1);
            }
        }
    }

    private static void getLongestPath(ArrayList<Integer> currentPath, Tree<Integer> root) {
        if (root.getChildren().size() > 0) {
            for (Tree<Integer> node : root.getChildren()) {
                if (node.getChildren() != null) {
                    currentPath.add(node.getValue());
                    getLongestPath(currentPath, node);
                }

                if (currentPath.size() > longestPath.size()) {
                    longestPath.clear();
                    for (int i = 0; i < currentPath.size(); i++) {
                        longestPath.add(currentPath.get(i));
                    }
                }

                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    private static void getPathsWithSumP(int P, Tree<Integer> root, ArrayList<Integer> currentPath, ArrayList<ArrayList<Integer>> pathsWithSumP) {
        if (root.getChildren().size() > 0) {
            for (Tree<Integer> node : root.getChildren()) {
                if (node.getChildren() != null) {
                    currentPath.add(node.getValue());
                    getPathsWithSumP(P, node, currentPath, pathsWithSumP);
                }

                if (getSumOfArray(currentPath) == P) {
                    pathsWithSumP.add(new ArrayList<>(currentPath));
                }

                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    private static int getSumOfArray(ArrayList<Integer> arrayList) {
        int sum = 0;

        for (int i = 0; i < arrayList.size(); i++) {
            sum += arrayList.get(i);
        }

        return sum;
    }
}


