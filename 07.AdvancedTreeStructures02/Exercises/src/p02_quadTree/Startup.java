package p02_quadTree;

public class Startup {
    public static void main(String[] args) {
        QuadTree quadTree = new QuadTree(0, 0, 200, 200);

        quadTree.insertElement(new Node(5, 5, 25, 25));
        quadTree.insertElement(new Node(10, 10, 35, 35));
        quadTree.insertElement(new Node(75, 75, 90, 90));
        quadTree.insertElement(new Node(85, 85, 87, 87));
        quadTree.insertElement(new Node(35, 35, 70, 70));


        quadTree.insertElement(new Node(60, 60, 120, 120));

        quadTree.insertElement(new Node(120, 120, 130, 130));
        quadTree.insertElement(new Node(150, 150, 160, 160));

        System.out.println("");
    }
}
