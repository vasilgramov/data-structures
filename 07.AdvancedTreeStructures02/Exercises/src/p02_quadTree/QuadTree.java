package p02_quadTree;

public class QuadTree {
    private static final int DEFAULT_MAX_DEPTH = 5;

    private Node root;

    public QuadTree(int x1, int y1, int x2, int y2) {
        this.setRoot(new Node(x1, y1, x2, y2));
    }

    public Node getRoot() {
        return root;
    }

    private void setRoot(Node root) {
        this.root = root;
    }

    public boolean insertElement(Node element) {
        if (!element.getBoundaries().isInside(this.root.getBoundaries())) {
            return false;
        }

        if (this.root.shouldSplit()) {
            this.root.split();
        } else {
            this.root.addElement(element);
        }

        return true;
    }
}