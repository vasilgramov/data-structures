package p03_arrayBasedStack;

import sun.plugin.dom.exception.InvalidAccessException;

public class ArrayStack<T> {
    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;

    private int count;

    private int endIndex;

    //----------------------------------------------------------
    public ArrayStack() {
        this.elements = (T[])new Object[INITIAL_CAPACITY];
        this.endIndex = this.elements.length - 1;
    }

    public ArrayStack(int capacity) {
        this.elements = (T[])new Object[capacity];
        this.endIndex = this.elements.length - 1;
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }
    //----------------------------------------------------------

    public void push(T element) {
        this.elements[this.endIndex] = element;
        this.endIndex--;
        this.setCount(this.getCount() + 1);
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
        if (this.getCount() == 0) {
            throw new InvalidAccessException("Stack is empty!");
        }

        T toReturn = this.elements[this.endIndex + 1];
        this.elements[this.endIndex + 1] = null;
        this.setCount(this.getCount() - 1);
        this.endIndex++;

        return toReturn;
    }

    public T peek() {
        if (this.getCount() == 0) {
            throw new InvalidAccessException("Stack is empty!");
        }

        return this.elements[this.endIndex + 1];
    }


}
