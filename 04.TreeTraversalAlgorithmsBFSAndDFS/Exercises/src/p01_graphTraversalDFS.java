import java.util.ArrayList;
import java.util.Scanner;

public class p01_graphTraversalDFS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<int[]> graph = new ArrayList<>();
        int numberOfLines = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numberOfLines; i++) {
            buildGraph(in, graph);
        }

        boolean[] hasBeenVisited = new boolean[graph.size()];
        ArrayList<ArrayList<Integer>> connectedComponents = new ArrayList<>();
        ArrayList<Integer> currentComponent = new ArrayList<>();

        for (int i = 0; i < graph.size(); i++) {
            if (!hasBeenVisited[i]) {
                DFS(hasBeenVisited, i, graph, currentComponent);

                connectedComponents.add(new ArrayList<>(currentComponent));
                currentComponent.clear();
            }
        }

        print(connectedComponents);
    }

    private static void print(ArrayList<ArrayList<Integer>> connectedComponents) {
        for (int i = 0; i < connectedComponents.size(); i++) {
            ArrayList<Integer> component = connectedComponents.get(i);

            System.out.print("Connected component: ");
            for (int j = 0; j < component.size(); j++) {
                System.out.print(component.get(j) + " ");
            }
            System.out.println();
        }
    }

    private static void buildGraph(Scanner in, ArrayList<int[]> graph) {
        String[] inputDataAsString = in.nextLine().split("\\s+");
        if (inputDataAsString[0].equals("")) {
            graph.add(new int[0]);
            return;
        }

        int[] currentComponent = new int[inputDataAsString.length];
        for (int j = 0; j <inputDataAsString.length; j++) {
            currentComponent[j] = Integer.parseInt(inputDataAsString[j]);
        }

        graph.add(currentComponent);
    }

    private static void DFS(boolean[] hasBeenVisited, int node, ArrayList<int[]> graph, ArrayList<Integer> currentComponent) {
        if (!hasBeenVisited[node]) {
            hasBeenVisited[node] = true;

            for (int child : graph.get(node)) {
                DFS(hasBeenVisited, child, graph, currentComponent);
            }

            currentComponent.add(node);
        }
    }
}
