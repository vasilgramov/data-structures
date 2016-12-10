package p06_reversedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ReversedArrayList<T> implements Iterable<T>{
    private ArrayList<T> elements;

    private Integer count;

    private Integer capacity;

    private Integer index;

    public ReversedArrayList() {
        this.elements = new ArrayList<>();
        setCount(0);
        setCapacity(1);
    }

    public ReversedArrayList(T[] elements) {
        setElements(elements);
        setCount(this.elements.size());
        setCapacity(this.elements.size() * 2);
    }

    public ArrayList<T> getElements() {
        return elements;
    }

    private void setElements(T[] elements) {
        this.elements = new ArrayList<T>(Arrays.asList(elements));
    }

    public Integer getCount() {
        return count;
    }

    private void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCapacity() {
        return capacity;
    }

    private void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void add(T element) {
        this.elements.add(element);
        this.count++;

        if (this.count.equals(this.capacity)) {
            this.capacity = this.count * 2;
        }
    }

    public void removeByIndex(int index) {
        this.elements.remove(this.elements.size() - 1 - index);

        this.count--;
    }

    public T getElementAt(int position) {
        return this.elements.get(this.elements.size() - 1 - position);
    }

    @Override
    public Iterator<T> iterator() {
        index = 0;
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (index < count) {
                    return true;
                }

                return false;
            }

            @Override
            public T next() {
                T toReturn = elements.get(index);
                index++;

                return toReturn;
            }
        };
    }
}
