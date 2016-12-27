package p01_binaryHeap;

import sun.plugin.dom.exception.InvalidAccessException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * this is a max binary heap
 *
 * down below i've added comments which will
 * help you to transfer it to min heap
 */

public class BinaryHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public BinaryHeap() {
        this.heap = new ArrayList<T>();
    }

    public BinaryHeap(T[] elements) {
        this.heap = new ArrayList<T>(Arrays.asList(elements));
        for (int i = this.heap.size() / 2; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public int count() {
        return this.heap.size();
    }

    public void add(T element) {
        this.heap.add(element);

        heapifyUp(this.heap.size() - 1);
    }

    public T extractMax() {
        if (this.heap.size() == 0) {
            throw new InvalidAccessException("Heap is empty!");
        }

        T toReturn = this.heap.get(0);

        this.heap.set(0, this.heap.get(this.heap.size() - 1));
        this.heap.remove(this.heap.size() - 1);

        if (this.heap.size() > 0) {
            heapifyDown(0);
        }

        return toReturn;
    }

    public T peek() {
        if (this.heap.size() == 0) {
            throw new InvalidAccessException("Heap is empty!");
        }

        return this.heap.get(0);
    }

    private void heapifyDown(int elementIndex) {
        int leftChildIndex = elementIndex * 2 + 1;
        int rightChildIndex = elementIndex * 2 + 2;
        int largest = elementIndex;

        if (this.heap.size() > leftChildIndex && this.heap.get(leftChildIndex).compareTo(this.heap.get(largest)) > 0) {     // change the last operator to  "<"
            largest = leftChildIndex;
        }

        if (this.heap.size() > rightChildIndex && this.heap.get(rightChildIndex).compareTo(this.heap.get(largest)) > 0) {   // change the last operator to  "<"
            largest = rightChildIndex;
        }

        if (largest != elementIndex) {
            T old = this.heap.get(elementIndex);
            this.heap.set(elementIndex, this.heap.get(largest));
            this.heap.set(largest, old);

            heapifyDown(largest);
        }
    }

    private void heapifyUp(int elementIndex) {
        int parentIndex = (elementIndex - 1) / 2;

        if (this.heap.get(parentIndex).compareTo(this.heap.get(elementIndex)) < 0) {    // change operator to  ">"
            T old = this.heap.get(elementIndex);
            this.heap.set(elementIndex, this.heap.get(parentIndex));
            this.heap.set(parentIndex, old);

            heapifyUp(parentIndex);
        }
    }
}
