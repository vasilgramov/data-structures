import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private T value;

    private Tree<T>[] children;

    //-------------------------------------------------
    public Tree(T value) {
        this.setValue(value);
    }

    public Tree(T value, Tree<T>... children) {
        this.setValue(value);

        this.setChildren(children);
    }

    public T getValue() {
        return value;
    }

    private void setValue(T value) {
        this.value = value;
    }

    public Tree<T>[] getChildren() {
        return children;
    }

    public void setChildren(Tree<T>[] children) {
        this.children = children;
    }
    //-------------------------------------------------

    public String print(int indent, StringBuilder builder) {
//        System.out.println(newString(" ", indent) + this.getValue());
        builder.append(newString(" ", indent) + this.getValue() + "\n");


        if (this.getChildren() != null) {
            for (Tree<T> child : this.getChildren()) {
                child.print(indent + 2, builder);
            }
        }

        return builder.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);

        if (this.getChildren() != null) {
            for (Tree<T> child : this.getChildren()) {
                child.each(consumer);
            }
        }
    }

    public Iterable<T> iterativeDFS() {
        List<T> elements = new ArrayList<>();
        Deque<Tree<T>> stack = new ArrayDeque<>();
        Set<Tree<T>> visited = new HashSet<>();

        stack.addFirst(this);
        while (!stack.isEmpty()) {
            Tree<T> tTree = stack.peekFirst();
            boolean hasFound = false;
            if (tTree.children != null) {
                for (Tree<T> child : tTree.children) {
                    if (!visited.contains(child)) {
                        stack.addFirst(child);
                        hasFound = true;
                    }
                }

                if (!hasFound) {
                    elements.add(tTree.value);

                    Tree<T> tTree1 = stack.removeFirst();
                    visited.add(tTree1);
                }
            } else {
                elements.add(tTree.value);

                Tree<T> tTree1 = stack.removeFirst();
                visited.add(tTree1);
            }
        }

        return elements;
    }

    public Iterable<T> iterativeDFS1() {
        Deque<T> elements = new ArrayDeque<>();
        Deque<Tree<T>> stack = new ArrayDeque<>();

        stack.addFirst(this);
        while (!stack.isEmpty()) {
            Tree<T> tTree = stack.removeFirst();

            if (tTree.children != null) {
                for (Tree<T> child : tTree.children) {
                    stack.addFirst(child);
                }
            }

            elements.addFirst(tTree.value);
        }

        return elements;
    }

    public Iterable<T> orderDFS() {
        List<T> elements = new ArrayList<>();
        this.DFS(this, elements);

        return elements;
    }

    private void DFS(Tree<T> tree, List<T> elements) {
        if (tree.children != null) {
            for (Tree<T> child : tree.children) {
                DFS(child, elements);
            }
        }

        elements.add(tree.value);
    }

    private String newString(String delimeter, int count) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            builder.append(delimeter);
        }

        return builder.toString();
    }


    @Override
    public String toString() {
        return this.value + "";
    }

    public Iterable<T> orderBFS() {
        List<T> elements = new ArrayList<>();
        Deque<Tree<T>> queue = new ArrayDeque<>();
        queue.addLast(this);

        while (!queue.isEmpty()) {
            Tree<T> tTree = queue.removeFirst();
            if (tTree.children != null) {
                for (Tree<T> child : tTree.children) {
                    queue.addLast(child);
                }
            }

            elements.add(tTree.value);
        }

        return elements;
    }
}
