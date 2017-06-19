package p02_roundDance;

import java.util.ArrayList;

public class Tree {
    private int value;

    private ArrayList<Tree> children;

    private int depth;

    //--------------------------------------------------------------
    public Tree(int value, int depth) {
        this.setValue(value);
        this.children = new ArrayList<>();

        this.setDepth(depth);
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Tree> getChildren() {
        return children;
    }

    public int getDepth() {
        return depth;
    }

    private void setDepth(int depth) {
        this.depth = depth;
    }
    //--------------------------------------------------------------

    public boolean addChild(Tree tree) {
        return this.children.add(tree);
    }

    public void print(int depth) {
        System.out.println(newString(" ", depth) + this.getValue());
        for (Tree child : this.getChildren()) {
            child.print(depth + 1);
        }
    }

    private String newString(String delimeter, int count) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            builder.append(delimeter);
        }

        return builder.toString();
    }
}
