package p02_quadTree;

public class Startup {
    public static void main(String[] args) {
        QuadTree quadTree = new QuadTree(0, 0, 200, 200);

        quadTree.insertElement(new Node(5, 5, 10, 10));
        quadTree.insertElement(new Node(10, 10, 15, 15));

        quadTree.insertElement(new Node(120, 120, 15, 15));
        quadTree.insertElement(new Node(75, 75, 10, 10));
        quadTree.insertElement(new Node(150, 150, 10, 10));

        System.out.println("");
    }
}
