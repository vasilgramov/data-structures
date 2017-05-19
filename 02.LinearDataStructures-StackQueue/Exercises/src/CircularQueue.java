public class CircularQueue<T> {
    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int startIndex;
    private int endIndex;

    private int size;

    public CircularQueue() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
    }

    public CircularQueue(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    private void grow() {
        T[] newArr = (T[]) new Object[this.elements.length * 2];

        this.copyAllElementsTo(newArr);

        this.elements = newArr;
        this.startIndex = 0;
        this.endIndex = this.size();
    }

    private void copyAllElementsTo(T[] newArr) {
        int sourceIndex = this.startIndex;
        int destIndex = 0;

        for (int i = 0; i < this.size(); i++) {
            newArr[destIndex] = this.elements[sourceIndex];
            sourceIndex = (sourceIndex + 1) % this.elements.length;
            destIndex++;
        }
    }

    public void enqueue(T element) {
        if (this.size() >= this.elements.length) {
            this.grow();
        }

        this.elements[this.endIndex] = element;
        this.endIndex = (this.endIndex + 1) % this.elements.length;
        this.size++;
    }


    public T dequeue() {
        if (this.size() == 0) {
            throw new IllegalArgumentException();
        }

        T element = this.elements[this.startIndex];
        this.startIndex = (this.startIndex + 1) % this.elements.length;
        this.size--;

        return element;
    }

    public T[] toArray() {
        T[] result = (T[]) new Object[this.size()];
        this.copyAllElementsTo(result);

        return result;
    }
}