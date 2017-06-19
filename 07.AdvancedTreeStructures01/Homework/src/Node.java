public class Node<T extends Comparable<T>> {

    public T value;
    public Node<T> left;
    public Node<T> right;
    public int height;

    Node(T key) {
        this.value = key;
        this.height = 1;
    }
}