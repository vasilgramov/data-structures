package p01_AVLTreeImplementation;

public class Node<T extends Comparable<T>> implements Comparable<T>{
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;

    private Node<T> parent;

    private int balanceFactor;

    private boolean isLeftChild;
    private boolean isRightChild;

//    private int childrenCount;        why ???

    //----------------------------------------------------------------------------
    public Node(T value) {
        this.setValue(value);

        this.setBalanceFactor(0);
//        this.setChildrenCount(0);
    }

    public Node(T value, Node<T> parent, boolean isLeftChild) {
        this.setValue(value);

        this.setParent(parent);

        this.setLeftChild(isLeftChild);
        this.setRightChild(!isLeftChild);

        this.setBalanceFactor(0);
//        this.setChildrenCount(0);
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

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public boolean isLeftChild() {
        return isLeftChild;
    }

    public void setLeftChild(boolean leftChild) {
        isLeftChild = leftChild;
    }

    public boolean isRightChild() {
        return isRightChild;
    }

    public void setRightChild(boolean rightChild) {
        isRightChild = rightChild;
    }

//    public int getChildrenCount() {
//        return childrenCount;
//    }

//    public void setChildrenCount(int childrenCount) {
//        this.childrenCount = childrenCount;
//    }

    //----------------------------------------------------------------------------

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    @Override
    public int compareTo(T o) {
        return this.getValue().compareTo(o);
    }
}
