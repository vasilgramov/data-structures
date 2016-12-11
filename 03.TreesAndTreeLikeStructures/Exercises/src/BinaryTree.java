import java.util.LinkedList;

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

    public void preOrderPrint(int indent) {
        System.out.println(newString(" ", indent) + this.getValue());

        if (this.leftChild != null) {
            this.leftChild.preOrderPrint(indent + 1);
        }

        if (this.rightChild != null) {
            this.rightChild.preOrderPrint(indent + 1);
        }
    }

    public void inOrderPrint(int indent) {
        if (this.leftChild != null) {
            this.leftChild.inOrderPrint(indent + 1);
        }

        System.out.println(newString(" ", indent + 1) + this.value);

        if (this.rightChild != null) {
            this.rightChild.inOrderPrint(indent + 1);
        }
    }

    public void postOrderPrint(int indent) {
        if (this.leftChild != null) {
            this.leftChild.postOrderPrint(indent + 1);
        }

        if (this.rightChild != null) {
            this.rightChild.postOrderPrint(indent + 1);
        }

        System.out.println(newString(" ", indent +1) + this.value);
    }

    private String newString(String delimeter, int count) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            builder.append(delimeter);
        }

        return builder.toString();
    }

}
