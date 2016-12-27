package p02_quadTree;

import java.util.ArrayList;

public class Node {
    private static final int MAX_ELEMENTS = 4;
    private static final int DEFAULT_MAX_DEPTH = 3;

    private Rectangle boundaries;
    private ArrayList<Node> elements;
    private Node[] children;
    private int depth;

    //--------------------------------------------------------------------------------------
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

    public Node[] getChildren() {
        return children;
    }

    private void setChildren(Node[] children) {
        this.children = children;
    }

    public int getDepth() {
        return depth;
    }

    private void setDepth(int depth) {
        this.depth = depth;
    }
    //--------------------------------------------------------------------------------------

    public boolean addElement(Node element, Node currentParent) {
        if (!element.getBoundaries().isInside(currentParent.getBoundaries())) {
            return false;
        }

        currentParent.getElements().add(element);
        if (shouldSplit(currentParent)) {
            split(currentParent);
        }

        if (currentParent.getChildren() != null) {
            for (int i = 0; i < currentParent.getChildren().length; i++) {
                if (element.getBoundaries().isInside(currentParent.getChildren()[i].getBoundaries())) {
                    currentParent.elements.remove(element);
                    currentParent.getChildren()[i].elements.add(element);

                    addElement(element, currentParent.getChildren()[i]);
                    break;
                }
            }
        }

        return true;
    }

    private void split(Node node) {
        node.setChildren(new Node[4]);
        Rectangle currentBoundaries = node.getBoundaries();

        initializeChildren(node, currentBoundaries);
    }

    private void initializeChildren(Node node, Rectangle currentBoundaries) {
        zeroChild(node, currentBoundaries);
        firstChild(node, currentBoundaries);
        secondChild(node, currentBoundaries);
        thirdChild(node, currentBoundaries);

        for (Node child : node.getChildren()) {
            child.setDepth(node.getDepth() + 1);
        }
    }

    private void thirdChild(Node node, Rectangle currentBoundaries) {
        node.getChildren()[3] = new Node(currentBoundaries.getX2() / 2,
                currentBoundaries.getY2() / 2,
                currentBoundaries.getX2(),
                currentBoundaries.getY2());
    }

    private void secondChild(Node node, Rectangle currentBoundaries) {
        node.getChildren()[2] = new Node(currentBoundaries.getX1(),
                currentBoundaries.getY2() / 2,
                currentBoundaries.getX2() / 2,
                currentBoundaries.getY2());
    }

    private void firstChild(Node node, Rectangle currentBoundaries) {
        node.getChildren()[1] = new Node(currentBoundaries.getX1(),
                currentBoundaries.getY1(),
                currentBoundaries.getX2() / 2,
                currentBoundaries.getY2() / 2);
    }

    private void zeroChild(Node node, Rectangle currentBoundaries) {
        node.getChildren()[0] = new Node(currentBoundaries.getX2() / 2,
                currentBoundaries.getY1(),
                currentBoundaries.getX2(),
                currentBoundaries.getY2() / 2);
    }

    private boolean shouldSplit(Node node) {
        return this.getElements().size() > MAX_ELEMENTS && node.getDepth() <= DEFAULT_MAX_DEPTH;
    }
}
