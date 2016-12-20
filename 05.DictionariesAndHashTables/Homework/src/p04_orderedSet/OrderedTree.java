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
        return searchElementDFS(this.root, element);
    }
    
    public void remove(T element) {
        if (this.root.compareTo(element) == 0) {
            this.allElements.remove(this.root.getValue());
            this.setCount(this.getCount() - 1);

            if (this.root.getRightChild() == null) {
                this.root = this.root.getLeftChild();
            } else {
                Node<T> leftChildOfTheRoot = this.root.getLeftChild();
                this.root = this.root.getRightChild();
                if (leftChildOfTheRoot != null) {
                    setLeftChild(this.root, leftChildOfTheRoot);
                }
            }
        } else if (contains(element)) {
            this.setCount(this.getCount() - 1);
            this.allElements.remove(element);

            removeElement(this.root, element);
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

    private boolean searchElementDFS(Node<T> root, T value) {
        if (root.compareTo(value) == 0) {
            return true;
        } else if (root.compareTo(value) > 0 && root.getLeftChild() != null) {
            return searchElementDFS(root.getLeftChild(), value);
        } else if (root.compareTo(value) < 0 && root.getRightChild() != null) {
            return searchElementDFS(root.getRightChild(), value);
        }

        return false;
    }

    private void removeElement(Node<T> currentNode, T value) {
        if (currentNode.getLeftChild().compareTo(value) == 0) {
            Node<T> leftNodeOfTheCurrent = currentNode.getLeftChild().getLeftChild();

            currentNode.setLeftChild(currentNode.getLeftChild().getRightChild());
            if (currentNode.getLeftChild() != null && leftNodeOfTheCurrent != null) {
                setLeftChild(currentNode.getLeftChild(), leftNodeOfTheCurrent);
            } else {
                currentNode.setLeftChild(leftNodeOfTheCurrent);
            }
        } else if (currentNode.getRightChild().compareTo(value) == 0) {
            Node<T> leftNodeOfTheCurrent = currentNode.getRightChild().getLeftChild();

            currentNode.setRightChild(currentNode.getRightChild().getRightChild());
            if (currentNode.getRightChild() != null && leftNodeOfTheCurrent != null) {
                setLeftChild(currentNode.getRightChild(), leftNodeOfTheCurrent);
            } else if (leftNodeOfTheCurrent != null){
                currentNode.setRightChild(leftNodeOfTheCurrent);
            }
        } else if (currentNode.compareTo(value) < 0) {
            removeElement(currentNode.getLeftChild(), value);
        } else if (currentNode.compareTo(value) > 0) {
            removeElement(currentNode.getLeftChild(), value);
        }
    }

    private void setLeftChild(Node<T> root, Node<T> leftChild) {
        if (root.getLeftChild() == null) {
            root.setLeftChild(leftChild);
        } else {
            setLeftChild(root.getLeftChild(), leftChild);
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
