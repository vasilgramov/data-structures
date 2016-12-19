package p04_orderedSet;

import java.util.*;

public class OrderedTree<T extends Comparable<T>> implements Iterable<T>{
    private static final int INITIAL_CAPACITY = 16;

    private LinkedList<T>[] slots;

    private int count;

    private Node<T> root;

    private ArrayList<T> allElements;

    //---------------------------------------------------------------
    public OrderedTree() {
        this.slots = new LinkedList[INITIAL_CAPACITY];

        this.setCount(0);

        this.allElements = new ArrayList<T>();
    }

    public OrderedTree(int capacity) {
        this.slots = new LinkedList[capacity];

        this.setCount(0);

        this.allElements = new ArrayList<T>();
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }
    //---------------------------------------------------------------

    public void add(T value) {
        if (this.root == null) {
            this.root = new Node<T>(value);

            int slotIndex = getSlotIndex(value);
            this.slots[slotIndex] = new LinkedList<T>();
            this.slots[slotIndex].addFirst(value);

            this.allElements.add(value);
        } else {
            boolean doesExist = false;

            int slotIndex = getSlotIndex(value);
            if (this.slots[slotIndex] == null) {
                this.slots[slotIndex] = new LinkedList<T>();
                this.slots[slotIndex].addFirst(value);
            } else {
                for (T element : this.slots[slotIndex]) {
                    if (element.equals(value)) {
                        doesExist = true;
                        this.setCount(this.getCount() - 1);
                    }
                }
            }

            if (!doesExist) {
                Node<T> nodeToAdd = new Node(value);

                addNewElementDFS(this.root, value);

                this.allElements.add(value);
            }
        }

        this.setCount(this.getCount() + 1);
    }

    public boolean contains(T element) {
        for (T currentElement : this.allElements) {
            if (currentElement.equals(element)) {
                return true;
            }
        }

        return false;
    }
    
    public void remove(T element) {
        if (this.contains(element)) {
            seachElementDFS(this.root, element);

            this.setCount(this.getCount() - 1);
        }
    }

    private boolean addNewElementDFS(Node<T> child, T value) {
        boolean isBigger = child.compareTo(value) < 0;
        Node<T> leftChild = child.getLeftChild();
        Node<T> rightChild = child.getRightChild();

        if (!isBigger && leftChild != null) {
            return addNewElementDFS(child.getLeftChild(), value);
        } else if (!isBigger && leftChild == null) {
            child.setLeftChild(new Node<T>(value));
            return true;
        } else if (isBigger && rightChild != null) {
            return addNewElementDFS(child.getRightChild(), value);
        } else if (isBigger && rightChild == null) {
            child.setRightChild(new Node<T>(value));
            return true;
        }

        return false;
    }

    private void seachElementDFS(Node<T> root, T value) {
        // TODO: remove element...

        boolean isBigger = root.compareTo(value) < 0;
        Node<T> leftChild = root.getLeftChild();
        Node<T> rightChild = root.getRightChild();

        if (!isBigger && leftChild.compareTo(value) < 0) {
            seachElementDFS(leftChild, value);
        } else if (!isBigger && leftChild.compareTo(value) == 0) {

        }
    }

    private int getSlotIndex(T value) {
        return Math.abs(value.hashCode() % this.slots.length);
    }

    @Override
    public Iterator<T> iterator() {
        Collections.sort(this.allElements);
        return this.allElements.iterator();
    }
}
