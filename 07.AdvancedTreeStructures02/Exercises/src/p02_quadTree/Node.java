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

    public boolean addElement(Node element, Node root) {
        if (!element.getBoundaries().isInside(root.getBoundaries())) {
            return false;
        }

        if (root.getChildren() == null) {
            root.getElements().add(element);
            if (shouldSplit(root)) {
                split(root);
            }
        } else {
            if (root.getChildren() != null) {
                distributeElements(root, element);
            }
        }

        return true;
    }

    private void split(Node node) {
        node.setChildren(new Node[4]);
        Rectangle currentBoundaries = node.getBoundaries();

        initializeChildren(node, currentBoundaries);

        ArrayList<Node> allElements = cloneAllElements(node.getElements());
        for (Node element : allElements) {
            for (Node child : node.getChildren()) {
                if (element.getBoundaries().isInside(child.getBoundaries())) {
                    child.elements.add(element);
                    node.elements.remove(element);
                    if (shouldSplit(child)) {
                        split(child);
                    }

                    break;
                }
            }
        }
    }

    private boolean distributeElements(Node node, Node element) {
        if (node.getChildren() != null) {
            for (Node child : node.getChildren()) {
                if (distributeElements(child, element)) {
                    return true;
                }
            }
        }

        if (element.getBoundaries().isInside(node.getBoundaries())) {
            node.elements.add(element);
            if (shouldSplit(node)) {
                split(node);
            }

            return true;
        }

        return false;
    }

    private ArrayList<Node> cloneAllElements(ArrayList<Node> allElements) {
        ArrayList<Node> clone = new ArrayList<>(allElements.size());

        for (Node element : allElements) {
            clone.add(element);
        }

        return clone;
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
        node.getChildren()[3] = new Node(currentBoundaries.getX1() + (currentBoundaries.getX2() - currentBoundaries.getX1()) / 2,
                currentBoundaries.getY1() + (currentBoundaries.getY2() - currentBoundaries.getY1()) / 2,
                currentBoundaries.getX2(),
                currentBoundaries.getY2());
    }

    private void secondChild(Node node, Rectangle currentBoundaries) {
        node.getChildren()[2] = new Node(currentBoundaries.getX1(),
                currentBoundaries.getY1() + (currentBoundaries.getY2() - currentBoundaries.getY1()) / 2,
                currentBoundaries.getX1() + (currentBoundaries.getX2() - currentBoundaries.getX1()) / 2,
                currentBoundaries.getY2());
    }

    private void firstChild(Node node, Rectangle currentBoundaries) {
        node.getChildren()[1] = new Node(currentBoundaries.getX1(),
                currentBoundaries.getY1(),
                currentBoundaries.getX1() + (currentBoundaries.getX2() - currentBoundaries.getX1()) / 2,
                currentBoundaries.getY1() + (currentBoundaries.getY2() - currentBoundaries.getY1()) / 2);
    }

    private void zeroChild(Node node, Rectangle currentBoundaries) {
        node.getChildren()[0] = new Node(currentBoundaries.getX1() + (currentBoundaries.getX2() - currentBoundaries.getX1()) / 2,
                currentBoundaries.getY1(),
                currentBoundaries.getX2(),
                currentBoundaries.getY1() + (currentBoundaries.getY2() - currentBoundaries.getY1()) / 2);
    }

    private boolean shouldSplit(Node node) {
        return node.getElements().size() > MAX_ELEMENTS && node.getDepth() < DEFAULT_MAX_DEPTH && node.getChildren() == null;
    }

    @Override
    public String toString() {
        return getBoundaries().getX1() + "-" + getBoundaries().getY1() + "-" + getBoundaries().getX2() + "-" + getBoundaries().getY2();
    }
}
