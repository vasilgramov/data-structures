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

    public void print(int spacesCount) {
        System.out.println(newString(" ", spacesCount) + this.getValue());

        if (this.getChildren() != null) {
            for (Tree<T> child : this.getChildren()) {
                child.print(spacesCount + 2);
            }
        }

    }

    public String newString(String delimeter, int count) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            builder.append(delimeter);
        }

        return builder.toString();
    }

}
