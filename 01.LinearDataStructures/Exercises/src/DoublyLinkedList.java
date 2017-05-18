import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int count;
    private ListNode<T> currentNode;

    public DoublyLinkedList() {
    }

    public int size() {
        return count;
    }

    private void setSize(int count) {
        this.count = count;
    }

    public void addFirst(T element) {
        if (size() == 0) {
            this.head = this.tail = new ListNode<>(element);
        } else {
            ListNode<T> newHead = new ListNode<>(element);
            newHead.setNextNode(this.head);
            this.head.setPrevNode(newHead);

            this.head = newHead;
        }

        setSize(size() + 1);
    }

    public void addLast(T element) {
        if (size() == 0) {
            this.head = this.tail = new ListNode<>(element);
        } else {
            ListNode<T> newTail = new ListNode<>(element);
            newTail.setPrevNode(this.tail);
            this.tail.setNextNode(newTail);

            this.tail = newTail;
        }

        setSize(size() + 1);
    }

    public T removeFirst() {
        if (size() == 0) {
            throw new IllegalArgumentException("Node is empty!");
        }

        T firstElement = this.head.getValue();

        this.head = this.head.getNextNode();
        if (this.head != null) {
            this.head.setPrevNode(null);
        } else {
            this.tail = null;
        }

        setSize(size() - 1);

        return firstElement;
    }

    public T removeLast() {
        if (size() == 0) {
            throw new IllegalArgumentException("Node is empty!");
        }

        T lastElement = this.tail.getValue();

        this.tail = this.tail.getPrevNode();
        if (this.tail != null) {
            this.tail.setNextNode(null);
        } else {
            this.head = null;
        }

        setSize(size() - 1);

        return lastElement;
    }


    public T[] toArray() {  // returns array of objects
        T[] arr = (T[]) new Object[size()];
        currentNode = this.head;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = currentNode.getValue();

            currentNode = currentNode.getNextNode();
        }

        return arr;
    }

    @Override
    public Iterator<T> iterator() {
        currentNode = this.head;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (currentNode == null) {
                    return false;
                }

                return true;
            }

            @Override
            public T next() {
                T res = currentNode.getValue();
                ListNode<T> next = currentNode.getNextNode();
                currentNode = next;

                return res;
            }
        };
    }

    private class ListNode<T> {

        private T value;
        private ListNode<T> nextNode;
        private ListNode<T> prevNode;

        public ListNode(T value) {
            setValue(value);
        }

        public T getValue() {
            return value;
        }

        private void setValue(T value) {
            this.value = value;
        }

        public ListNode<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(ListNode<T> nextNode) {
            this.nextNode = nextNode;
        }

        public ListNode<T> getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(ListNode<T> prevNode) {
            this.prevNode = prevNode;
        }
    }
}