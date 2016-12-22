package p01_AVLTreeImplementation;

import java.util.ArrayList;
import java.util.Iterator;

public class AVLTree<T> implements Iterable<T>{
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
        } else if (root.compareTo(element) > 0){
            if (root.getLeftChild() == null) {
                Node<T> elementToAdd = new Node<T>(element, root, true);
                root.setLeftChild(elementToAdd);

//                root.setChildrenCount(root.getChildrenCount() + 1);
                root.setBalanceFactor(root.getBalanceFactor() + 1);

                if (root.getBalanceFactor() < -1 || root.getBalanceFactor() > 1) {
                    //todo retrace
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

                if (root.getBalanceFactor() < -1 || root.getBalanceFactor() > 1) {
                    //todo retrace
                }
            } else {
                return insertInternal(root.getRightChild(), element);
            }
        }

        return false;
    }


    @Override
    public Iterator<T> iterator() {
        return this.allElements.iterator();
    }
}
