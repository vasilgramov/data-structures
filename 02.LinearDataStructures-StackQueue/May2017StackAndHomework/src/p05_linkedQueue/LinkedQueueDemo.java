package p05_linkedQueue;

public class LinkedQueueDemo<E> {

    private Node<E> head;
    private Node<E> last;

    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (this.head == null) {
            this.head = new Node<>();
            this.head.value = element;

            this.last = this.head;
        } else {
            Node<E> newTail = new Node<>();
            newTail.value = element;

            this.last.next = newTail;
            this.last = newTail;
        }

        this.setSize(this.size() + 1);
    }

    public E dequeue() {
        if (this.size() == 0) {
            throw new IllegalArgumentException("Queue is empty!");
        }

        E value = this.head.value;

        this.head = this.head.next;
        this.setSize(this.size() - 1);

        return value;
    }

    public E[] toArray() {
        E[] toReturn = (E[]) new Object[this.size()];

        Node<E> tempHead = this.head;

        int index = 0;
        while (tempHead != null) {
            toReturn[index++] = tempHead.value;
            tempHead = tempHead.next;
        }

        return toReturn;
    }

    private class Node<E> {
        private E value;
        private Node<E> next;
    }


}
