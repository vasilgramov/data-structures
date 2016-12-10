package p07_linkedList;

import sun.plugin.dom.exception.InvalidAccessException;

import java.nio.file.AccessDeniedException;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{
    private ListNode<T> head;

    private ListNode<T> tail;

    private ListNode<T> current;

    private Integer count;

    public LinkedList() {
        setCount(0);
    }

    //-----------------------------------------

    public ListNode<T> getHead() {
        return head;
    }

    private void setHead(ListNode<T> head) {
        this.head = head;
    }

    public Integer getCount() {
        return count;
    }

    private void setCount(Integer count) {
        this.count = count;
    }

    //-----------------------------------------

    public void add(T element) {
        if (getCount() == 0) {
            this.head = this.tail = new ListNode<>(element, this.count);
        } else {
            ListNode<T> newTail = new ListNode<>(element, this.count);
            this.tail.setNextElement(newTail);

            this.tail = newTail;
        }

        setCount(getCount() + 1);
    }

    public void removeAt(int index) {
        if (index == getCount() || index < 0) {
            throw new InvalidAccessException("Index must be positive and lower that LinkedList size!");
        }

        ListNode<T> currentNode = this.head;

        while (true) {
            if (currentNode.getIndex() + 1 == index) {
                currentNode.nextElement = currentNode.nextElement.getNextElement();

                while (currentNode.getNextElement() != null) {
                    currentNode.nextElement.index = currentNode.nextElement.index - 1;
                    currentNode = currentNode.getNextElement();
                }

                return;
            } else {
                currentNode = currentNode.getNextElement();
                if (index + 1 == getCount() && currentNode.getNextElement().equals(this.tail)) {
                    currentNode.nextElement = null;
                    this.tail = currentNode;
                    return;
                }
            }
        }
    }

    public int firstIndexOf(T element) {
        current = this.head;
        while (current != null) {
            if (current.getValue().equals(element)) {
                return current.getIndex();
            }

            current = current.getNextElement();
        }

        return -1;
    }

    public int lastIndexOf(T element) {
        int toReturn = -1;
        current = this.head;
        while (current != null) {
            if (current.getValue().equals(element)) {
                toReturn = current.getIndex();
            }

            current = current.getNextElement();
        }

        return toReturn;
    }

    @Override
    public Iterator<T> iterator() {
        current = this.head;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (current != null) {
                    return true;
                }

                return false;
            }

            @Override
            public T next() {
                ListNode<T> toReturn = current;
                current = current.getNextElement();
                return toReturn.getValue();
            }
        };
    }

    private class ListNode<T> {
        private T value;

        private ListNode<T> nextElement;

        private int index;

        public ListNode(T value, int index) {
            setValue(value);
            setIndex(index);
        }

        public T getValue() {
            return value;
        }

        private void setValue(T value) {
            this.value = value;
        }

        public ListNode<T> getNextElement() {
            return nextElement;
        }

        public void setNextElement(ListNode<T> nextElement) {
            this.nextElement = nextElement;
        }

        public int getIndex() {
            return index;
        }

        private void setIndex(int index) {
            this.index = index;
        }
    }
}
