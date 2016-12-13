package p01_playWithTree;

import java.util.ArrayList;

public class Tree<T> {
    private T parent;

    private T value;

    private ArrayList<Tree<T>> children;

    //-----------------------------------------------------
    public Tree(T value) {
        this.setValue(value);
        this.children = new ArrayList<>();

        this.setParentTo(parent);
    }

    public Tree(T value, Tree<T> child) {
        this.setValue(value);

        this.children = new ArrayList<>();
        if (child != null) {
            this.children.add(child);
            for (Tree<T> childAsChild : this.children) {
                childAsChild.setParentTo(value);
            }
        }
    }

    public T getValue (){
        return this.value;
    }

    private void setValue(T value) {
        this.value = value;
    }

    public ArrayList<Tree<T>> getChildren() {
        return this.children;
    }
    //-----------------------------------------------------

    public boolean addChild(Tree<T> child) {
        return this.children.add(child);
    }

    public void setParentTo(T value) {
        this.parent = value;
    }
}
