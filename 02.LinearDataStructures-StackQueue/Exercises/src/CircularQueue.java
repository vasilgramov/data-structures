import sun.plugin.dom.exception.InvalidAccessException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CircularQueue<T> implements Iterable<T> {
    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;

    private int startIndex;

    private int endIndex;

    private int count;

    //-------------------------------------------------------
    public CircularQueue() {
        this.elements = (T[])new Object[INITIAL_CAPACITY];
    }

    public CircularQueue(int capacity) {
        this.elements = (T[])new Object[capacity];
    }


    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }
    //-------------------------------------------------------

    public void enqueue(T element) {
        if (this.count >= this.elements.length) {
            this.grow();
        }

        this.elements[this.endIndex] = element;

        if (this.endIndex == this.elements.length) {   // reached the end ?
            this.endIndex = 0;
        } else {
            this.endIndex++;
        }

        this.setCount(this.getCount() + 1);
    }

    private void grow() {
        T[] newArray = (T[])new Object[this.elements.length * 2];

        copyElements(newArray);

        this.elements = newArray;
        this.startIndex = 0;
        this.endIndex = this.count;
    }

    private void copyElements(T[] newArray) {
        if (this.startIndex < endIndex) {
            for (int i = this.startIndex; i < this.endIndex; i++) {
                newArray[i] = this.elements[i];
            }
        } else {
            int index = 0;
            for (int i = startIndex; i < this.elements.length; i++) {
                newArray[index] = this.elements[i];
                index++;
            }

            for (int i = 0; i < this.endIndex - 1; i++) {
                newArray[index] = this.elements[i];
                index++;
            }
        }
    }

    public T dequeue() {
        if (this.count == 0) {
            throw new InvalidAccessException("Queue is empty!");
        }

        this.count--;
        this.endIndex--;
        if (endIndex < 0) {
            endIndex = this.elements.length - 1;
        }

        T toReturn = this.elements[this.endIndex];

        this.elements[this.endIndex] = null;

        return toReturn;
    }

    public T peek() {
        if (this.count == 0) {
            throw new InvalidAccessException("Queue is empty!");
        }

        if (this.endIndex - 1 < 0) {
            return  this.elements[this.elements.length - 1];
        }

        return this.elements[this.endIndex - 1];
    }

    public T[] toArray() {  // returns array of objects
        return getAllElements();
    }

    @Override
    public Iterator<T> iterator() {
        T[] nonRedunantElements = getAllElements();
        return Arrays.stream(nonRedunantElements).iterator();
    }

    private T[] getAllElements() {
        ArrayList<T> elements = new ArrayList<>();
        if (endIndex > startIndex) {
            for (int i = endIndex - 1; i >= startIndex; i--) {
                elements.add(this.elements[i]);
            }
        } else {
            for (int i = startIndex; i < this.count; i++) {
                elements.add(this.elements[i]);
            }

            for (int i = 0; i < this.endIndex - 1; i++) {
                elements.add(this.elements[i]);
            }
        }

        return elements.toArray((T[])new Object[elements.size()]);
    }
}
