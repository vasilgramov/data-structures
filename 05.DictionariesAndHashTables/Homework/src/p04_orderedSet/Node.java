package p04_orderedSet;

public class Node<T extends Comparable<T>> implements Comparable<T>{
    private T value;

    private Node<T> leftChild;

    private Node<T> rightChild;

    //------------------------------------------------------
    public Node(T value) {
        this.setValue(value);
    }

    public T getValue() {
        return value;
    }

    private void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }
    //------------------------------------------------------

    @Override
    public int compareTo(T o) {
        return this.getValue().compareTo(o);
    }

}
