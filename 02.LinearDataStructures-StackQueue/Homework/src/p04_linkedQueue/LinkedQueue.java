//package p04_linkedQueue;

public class LinkedQueue<T> {
    private QueueNode<T> head;

    private QueueNode<T> tail;

    private QueueNode<T> currentNode;

    private int size;

    //---------------------------------------------------------
    public LinkedQueue() {
        setCount(0);
    }

    public int size() {
        return size;
    }

    private void setCount(int count) {
        this.size = count;
    }
    //---------------------------------------------------------

    public void enqueue(T element) {
        if (this.size() == 0) {
            this.head = this.tail = new QueueNode<>(element);
        }else {
            QueueNode<T> newTail = new QueueNode<>(element);
            newTail.setPrevNode(this.tail);
            this.tail.setNextNode(newTail);
            this.tail = newTail;
        }

        this.setCount(this.size() + 1);
    }

    public T dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException("Queue is empty!");
        }

        T toReturn = this.head.getValue();
        QueueNode<T> newHead = this.head.getNextNode();
        this.head = newHead;
        this.setCount(this.size() - 1);

        return toReturn;
    }

    public T peek() {
        return this.head.getValue();
    }

    public T[] toArray() {
        T[] toReturn = (T[]) new Object[this.size()];

        QueueNode<T> headCopy = this.head;
        int index = 0;
        while (headCopy != null) {
            toReturn[index++] = headCopy.value;
            headCopy = headCopy.nextNode;
        }

        if (this.head == null && this.size != 0) {
            throw new IllegalArgumentException("???");
        }

        return toReturn;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    private class QueueNode<T> {
        private T value;

        private QueueNode<T> nextNode;

        private QueueNode<T> prevNode;

        //------------------------------------------------------
        public QueueNode(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        private void setValue(T value) {
            this.value = value;
        }

        public QueueNode<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(QueueNode<T> nextNode) {
            this.nextNode = nextNode;
        }

        public QueueNode<T> getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(QueueNode<T> prevNode) {
            this.prevNode = prevNode;
        }
        //------------------------------------------------------
    }
}
