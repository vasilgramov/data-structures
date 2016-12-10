package p04_linkedQueue;

import sun.plugin.dom.exception.InvalidAccessException;

public class LinkedQueue<T> {
    private QueueNode<T> head;

    private QueueNode<T> tail;

    private QueueNode<T> currentNode;

    private int count;

    //---------------------------------------------------------
    public LinkedQueue() {
        setCount(0);
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }
    //---------------------------------------------------------

    public void enqueue(T element) {
        if (this.getCount() == 0) {
            this.head = this.tail = new QueueNode<>(element);
        }else {
            QueueNode<T> newTail = new QueueNode<>(element);
            newTail.setPrevNode(this.tail);
            this.tail.setNextNode(newTail);
            this.tail = newTail;
        }

        this.setCount(this.getCount() + 1);
    }

    public T dequeue() {
        T toReturn = this.head.getValue();
        QueueNode<T> newHead = this.head.getNextNode();
        this.head = newHead;
        this.setCount(this.getCount() - 1);

        return toReturn;
    }

    public T peek() {
        return this.head.getValue();
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
