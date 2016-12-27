package p02_quadTree;

import java.util.ArrayList;

public class Node {
    private static final int MAX_ELEMENTS = 4;

    private Rectangle boundaries;
    private ArrayList<Node> elements;

    public Node(int x1, int y1, int x2, int y2) {
        this.setBoundaries(new Rectangle(x1, y1, x2, y2));
        this.setElements(new ArrayList<Node>());
    }

    public Rectangle getBoundaries() {
        return boundaries;
    }

    private void setBoundaries(Rectangle boundaries) {
        this.boundaries = boundaries;
    }

    public ArrayList<Node> getElements() {
        return elements;
    }

    private void setElements(ArrayList<Node> elements) {
        this.elements = elements;
    }


}
