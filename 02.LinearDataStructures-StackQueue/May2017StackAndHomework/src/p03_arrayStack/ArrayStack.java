package p03_arrayStack;

public class ArrayStack<T> {
    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;

    private int size;

    private int endIndex;

    public ArrayStack() {
        this.elements = (T[])new Object[INITIAL_CAPACITY];
        this.endIndex = this.elements.length - 1;
    }

    public ArrayStack(int capacity) {
        this.elements = (T[])new Object[capacity];
        this.endIndex = this.elements.length - 1;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int count) {
        this.size = count;
    }

    public void push(T element) {
        this.elements[this.endIndex] = element;
        this.endIndex--;
        this.setSize(this.size() + 1);
        if (this.endIndex < 0) {
            grow();
            this.endIndex = this.elements.length / 2 - 1;
        }
    }

    private void grow() {
        T[] newArr = (T[])new Object[2 * this.elements.length];
        int index = 0;
        for (int i = this.elements.length; i < newArr.length; i++) {
            newArr[i] = this.elements[index];
            index++;
        }

        this.elements = newArr;
    }

    public T pop() {
        if (this.size() == 0) {
            throw new IllegalArgumentException("Stack is empty!");
        }

        T toReturn = this.elements[this.endIndex + 1];
        this.elements[this.endIndex + 1] = null;
        this.setSize(this.size() - 1);
        this.endIndex++;

        return toReturn;
    }

    public T peek() {
        if (this.size() == 0) {
            throw new IllegalArgumentException("Stack is empty!");
        }

        return this.elements[this.endIndex + 1];
    }

    public T[] toArray() {
        T[] toReturn = (T[]) new Object[this.size()];
        for (int i = 0; i < this.size(); i++) {
            toReturn[toReturn.length - i - 1] = this.elements[this.elements.length - i - 1];
        }

        return toReturn;
    }

}
