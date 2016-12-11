public class BinaryTree<T> {
    private T value;

    private BinaryTree<T> leftChild;

    private BinaryTree<T> rightChild;

    //------------------------------------------------------------------
    public BinaryTree(T value) {
        this.setValue(value);
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.setValue(value);
        this.setLeftChild(leftChild);
        this.setRightChild(rightChild);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTree<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTree<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTree<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTree<T> rightChild) {
        this.rightChild = rightChild;
    }
    //------------------------------------------------------------------

    public void print(int indent) {
        System.out.println(newString(" ", indent) + this.getValue());

        if (this.leftChild != null) {
            this.leftChild.print(indent + 1);
        }

        if (this.rightChild != null) {
            this.rightChild.print(indent + 1);
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
