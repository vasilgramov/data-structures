package p04_longestPathInATree;

import java.util.ArrayList;

public class Tree {
    private int value;

    private ArrayList<Tree> children;

    //-----------------------------------------------------
    public Tree(int value) {
        this.setValue(value);
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.children = new ArrayList<>();

        this.value = value;
    }

    public ArrayList<Tree> getChildren() {
        return children;
    }
    //-----------------------------------------------------

    public boolean addChild(Tree child) {
        return this.children.add(child);
    }
}
