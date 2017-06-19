import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    private int height(Node<T> N) {
        if (N == null) {
            return 0;
        }

        return N.height;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        if (item.compareTo(node.value) < 0) {
            return search(node.left, item);
        } else if (item.compareTo(node.value) > 0) {
            return search(node.right, item);
        }

        return node;
    }

    private Node<T> insert(Node<T> node, T key) {
        if (node == null) {
            return (new Node<>(key));
        }

        if (key.compareTo(node.value) < 0) {
            node.left = insert(node.left, key);
        } else if (key.compareTo(node.value) > 0) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.value) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.value) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && key.compareTo(node.left.value) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.value) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node<T> leftRotate(Node<T> node) {
        Node<T> right = node.right;
        Node<T> left = right.left;

        right.left = node;
        node.right = left;

        node.height = max(height(node.left), height(node.right)) + 1;
        right.height = max(height(right.left), height(right.right)) + 1;

        return right;
    }

    private Node<T> rightRotate(Node<T> node) {
        Node<T> left = node.left;
        Node<T> right = left.right;

        left.right = node;
        node.left = right;

        node.height = max(height(node.left), height(node.right)) + 1;
        left.height = max(height(left.left), height(left.right)) + 1;

        return left;
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int getBalance(Node<T> N) {
        if (N == null) {
            return 0;
        }

        return height(N.left) - height(N.right);
    }

    public void insert(T key) {
        this.root = this.insert(this.root, key);
    }
}