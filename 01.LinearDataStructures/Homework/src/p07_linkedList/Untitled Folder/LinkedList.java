import java.util.Iterator;

/**
 * Created by vladix on 5/23/17.
 */
public class LinkedList<E> implements Iterable<E> {

    private Node head;
    private Node tail;

    private int size;

    public int size() {
        return this.size;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    public void addFirst(E item)
    {
        Node old = this.head;
        this.head = new Node(item).next = old ;

        if (this.isEmpty()) {
            this.tail = this.head;
        }

        this.size++;
    }

    public void addLast(E item)
    {
        Node old = this.tail;

        this.tail = new Node(item);

        if (this.isEmpty()) {
            this.head = this.tail;
        } else {
            old.next = this.tail;
        }

        this.size++;
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        E item = this.head.value;

        this.head = this.head.next;

        this.size--;

        if (this.isEmpty()) {
            this.tail = null;
        }

        return item;
    }

    public E removeLast() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException();
        }

        E item = this.tail.value;

        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            Node newTail = this.getSecondToLast();
            newTail.next = null;
            this.tail = newTail;
        }

        this.size--;

        return item;
    }

    private Node getSecondToLast() {
        Node node = this.head;

        while (node.next != this.tail) {
            node = node.next;
        }

        return node;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node cursor;

        @Override
        public boolean hasNext() {
            return this.cursor.next != null;
        }

        @Override
        public E next() {
            E toReturn = this.cursor.value;
            this.cursor = this.cursor.next;
            return toReturn;
        }
    }


    private class Node
    {
        private E value;
        private Node next;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return this.value;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }
}

