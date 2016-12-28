package p02_quadTree;

import java.util.ArrayList;

public class Startup {
    public static void main(String[] args) {
        QuadTree quadTree = new QuadTree(0, 0, 200, 200);

        quadTree.insertElement(new Node(5, 5, 25, 25));
        quadTree.insertElement(new Node(10, 10, 35, 35));
        quadTree.insertElement(new Node(75, 75, 90, 90));
        quadTree.insertElement(new Node(85, 85, 87, 87));
        quadTree.insertElement(new Node(35, 35, 70, 70));

        quadTree.insertElement(new Node(60, 60, 120, 120));

        quadTree.insertElement(new Node(127, 120, 130, 122));
        quadTree.insertElement(new Node(127, 120, 130, 122));
        quadTree.insertElement(new Node(127, 120, 130, 122));
        quadTree.insertElement(new Node(127, 120, 130, 122));
        quadTree.insertElement(new Node(127, 120, 130, 122));

        quadTree.insertElement(new Node(150, 150, 160, 160));
        quadTree.insertElement(new Node(125, 130, 135, 140));
        quadTree.insertElement(new Node(110, 150, 120, 160));
        quadTree.insertElement(new Node(111, 111, 122, 122));

        System.out.println("");

        ArrayList<Node> nodes = quadTree.getQuadrant(0, 0, 200, 200);
        for (Node node : nodes) {
            System.out.println(node);
        }
    }
}
