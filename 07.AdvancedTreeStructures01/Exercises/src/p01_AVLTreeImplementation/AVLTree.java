package p01_AVLTreeImplementation;

import java.util.ArrayList;
import java.util.Iterator;

public class AVLTree<T extends Comparable<T>>/* implements Iterable<T> */{
    private Node<T> root;

    private int count;

    private ArrayList<T> allElements;  // use TreeSet for sorted elements;

    //----------------------------------------------------------
    public AVLTree() {
        this.allElements = new ArrayList<T>();

        this.setCount(0);
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public Node<T> getRoot() {
        return root;
    }

    private void setRoot(Node<T> root) {
        this.root = root;
    }
    //----------------------------------------------------------

    public void add(T element) {
        boolean doesExist = false;

        if (this.getRoot() == null) {
            this.setRoot(new Node<T>(element));
        } else {
            doesExist = this.insertInternal(this.root, element);
        }

        if (!doesExist) {
            this.setCount(this.getCount() + 1);
            this.allElements.add(element);
        }
    }

    private boolean insertInternal(Node<T> root, T element) {
        if (root.compareTo(element) == 0) {
            return true;
        } else if (root.compareTo(element) > 0) {
            if (root.getLeftChild() == null) {
                Node<T> elementToAdd = new Node<T>(element, root, true);
                root.setLeftChild(elementToAdd);

//                root.setChildrenCount(root.getChildrenCount() + 1);
                root.setBalanceFactor(root.getBalanceFactor() + 1);

                if (root.getBalanceFactor() <= -1 || root.getBalanceFactor() >= 1) {
                    retrace(root, true);
                }
            } else {
                return insertInternal(root.getLeftChild(), element);
            }
        } else if (root.compareTo(element) < 0) {
            if (root.getRightChild() == null) {
                Node<T> elementToAdd = new Node<T>(element, root, false);
                root.setRightChild(elementToAdd);

//                root.setChildrenCount(root.getChildrenCount() + 1);
                root.setBalanceFactor(root.getBalanceFactor() - 1);

                if (root.getBalanceFactor() <= -1 || root.getBalanceFactor() >= 1) {
                    retrace(root, false);
                }
            } else {
                return insertInternal(root.getRightChild(), element);
            }
        }

        return false;
    }

    private void retrace(Node<T> node, boolean isLeftChild) {
        if (node.getParent() == null) {
            return;
        }

        if (node.isLeftChild() && node.getBalanceFactor() != 0) {
            node.getParent().setBalanceFactor(node.getParent().getBalanceFactor() + 1);
            if (node.getParent().getBalanceFactor() < -1 || node.getParent().getBalanceFactor() > 1) {
                if (node.isLeftChild() && isLeftChild) {
                    leftLeftRotation(node);
                } else if (node.isLeftChild() && !isLeftChild){
                    leftRightRotation(node);
                }

                return;
            }
        } else if (node.isRightChild() && node.getBalanceFactor() != 0) {
            node.getParent().setBalanceFactor(node.getParent().getBalanceFactor() - 1);
            if (node.getParent().getBalanceFactor() < -1 || node.getParent().getBalanceFactor() > 1) {
                if (node.isRightChild() && !isLeftChild) {
                    rightRightRotation(node);
                } else if (node.isRightChild() && isLeftChild) {
                    rightLeftRotation(node);
                }

                return;
            }
        }

        retrace(node.getParent(), node.isLeftChild());
    }


    private void leftRightRotation(Node<T> node) {
        System.out.println("left-right");

        Node<T> rightChild = node.getRightChild();
        Node<T> leftChildOfTheNode = rightChild.getLeftChild();

        rightChild.setLeftChild(null);

        if (leftChildOfTheNode != null) {
            leftChildOfTheNode.setParent(node);
        }
        node.setRightChild(leftChildOfTheNode);

        Node<T> parent = node.getParent();
        node.setParent(rightChild);
        rightChild.setLeftChild(node);
        rightChild.setParent(parent);
        parent.setLeftChild(rightChild);

        leftLeftRotation(rightChild);
    }

    private void leftLeftRotation(Node<T> node) {
        System.out.println("left-left");

        Node<T> parent = node.getParent();
        Node<T> rightChildOfTheNode = node.getRightChild();
        if (rightChildOfTheNode != null) {
            rightChildOfTheNode.setParent(parent);
        }
        parent.setLeftChild(rightChildOfTheNode);

        if (parent.getParent() != null) {
            parent.getParent().setLeftChild(node);
        }

        parent.setParent(node);

        if (this.root.getValue().compareTo(parent.getValue()) == 0) {
            node.setRightChild(parent);

            this.root = node;
            node.setParent(null);
        } else {
            node.setRightChild(parent);
        }

        node.setBalanceFactor(0);
        parent.setBalanceFactor(0);
    }

    private void rightLeftRotation(Node<T> node) {
//        System.out.println("right-left");


    }

    private void rightRightRotation(Node<T> node) {
//        System.out.println("right-right");

    }

    private void setDeepLeftChid(Node<T> node, Node<T> toAdd) {
        if (node.getLeftChild() != null) {
            node.setLeftChild(toAdd);
        } else {
            setDeepLeftChid(node.getLeftChild(), toAdd);
        }
    }

//    @Override
//    public Iterator<T> iterator() {
//        return this.allElements.iterator();
//    }
}
