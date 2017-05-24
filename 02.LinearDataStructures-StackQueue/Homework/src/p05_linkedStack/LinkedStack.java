package p05_linkedStack;

public class LinkedStack<E> implements Comparable<E> {

    private Node<E> head;

    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        this.head = new Node(element, this.head);

        this.setSize(this.size() + 1);
    }

    public E pop() {
        if (this.size() == 0) {
            throw new IllegalArgumentException("The stack is empty");
        }
        Node<E> node = this.head;
        this.head = this.head.nextNode;
        this.size--;

        return node.value;
    }

    public E[] toArray()
    {
        E[] arr = (E[]) new Object[this.size];

        int pos = 0;
        Node<E> curr;

        while ((curr = this.head) != null) {
            arr[pos++] = curr.value;
            this.head = this.head.nextNode;
        }

        return arr;
    }

    @Override
    public int compareTo(E o) {
        return 0;
    }

    private class Node<E> {

        private E value;

        public Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public E getValue() {
            return this.value;
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

    }

}