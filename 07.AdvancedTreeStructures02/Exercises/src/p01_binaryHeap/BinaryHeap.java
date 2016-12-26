package p01_binaryHeap;

import java.util.ArrayList;
import java.util.Arrays;

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

    public void add(T element) {
        this.heap.add(element);

        heapifyUp(this.heap.size() - 1);
    }

    private void heapifyDown(int elementIndex) {
        int leftChildIndex = elementIndex * 2 + 1;
        int rightChildIndex = elementIndex * 2 + 2;
        int largest = elementIndex;

        if (this.heap.size() > leftChildIndex && this.heap.get(leftChildIndex).compareTo(this.heap.get(largest)) > 0) {
            largest = leftChildIndex;
        }

        if (this.heap.size() > rightChildIndex && this.heap.get(rightChildIndex).compareTo(this.heap.get(largest)) > 0) {
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

        if (this.heap.get(parentIndex).compareTo(this.heap.get(elementIndex)) < 0) {
            T old = this.heap.get(elementIndex);
            this.heap.set(elementIndex, this.heap.get(parentIndex));
            this.heap.set(parentIndex, old);

            heapifyUp(parentIndex);
        }
    }
}
