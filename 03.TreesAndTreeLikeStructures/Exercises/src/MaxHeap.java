import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vladix on 4/30/17.
 */
public class MaxHeap {

    public static void main(String[] args) {
        // MaxHeap with Priority Queue

        String str = "25, 55, 54, 38, 53\n" +
                "81, 93, 19, 33, 31\n" +
                "77, 69, 85, 2, 69\n" +
                "48, 33, 35, 31, 14\n" +
                "70, 46, 52, 90, 62\n" +
                "12, 72, 74, 90, 73\n" +
                "93, 5, 65, 58, 16\n" +
                "89, 74, 80, 86, 83\n" +
                "81, 29, 87, 95, 4\n" +
                "98, 85, 84, 66, 61\n" +
                "84, 76, 92, 58, 61\n" +
                "54, 16, 61, 24, 9\n" +
                "42, 79, 49, 59, 83\n" +
                "36, 77, 20, 89, 55\n" +
                "48, 100, 35, 35, 40\n" +
                "29, 70, 87, 62, 70\n" +
                "53, 40, 35, 10, 52\n" +
                "10, 80, 29, 40, 55\n" +
                "14, 96, 8, 68, 4\n" +
                "28, 58, 100, 40, 6";

        System.out.println(str.replace("\n", ""));
    }

    public static class PriorityQueue<T extends Comparable<T>> {
        private Map<T, Integer> searchCollection;
        private List<T> heap;

        public PriorityQueue() {
            this.heap = new ArrayList<>();
            this.searchCollection = new HashMap<T, Integer>();
        }

        public int size() {
            return this.heap.size();
        }

        public T peekMin() {
            return this.heap.get(0);
        }

        public boolean contains(T element) {
            return this.searchCollection.containsKey(element);
        }

        public void enqueue(T element) {
            this.searchCollection.put(element, this.heap.size());
            this.heap.add(element);
            this.heapifyUp(this.heap.size() - 1);
        }

        public T extractMax() {
            T max = this.heap.get(0);
            T last = this.heap.get(this.heap.size() - 1);
            this.searchCollection.put(last, 0);
            this.heap.set(0, last);
            this.heap.remove(this.heap.size() - 1);
            if (this.heap.size() > 0) {
                this.heapifyDown(0);
            }

            this.searchCollection.remove(max);

            return max;
        }

        public void decreaseKey(T element) {
            int index = this.searchCollection.get(element);
            this.heapifyUp(index);
        }

        private void heapifyDown(int i) {
            int left = (2 * i) + 1;
            int right = (2 * i) + 2;
            int biggest = i;

            if (left < this.heap.size() && this.heap.get(left).compareTo(this.heap.get(biggest)) > 0) {
                biggest = left;
            }

            if (right < this.heap.size() && this.heap.get(right).compareTo(this.heap.get(biggest)) > 0) {
                biggest = right;
            }

            if (biggest != i) {
                T old = this.heap.get(i);
                this.searchCollection.put(old, biggest);
                this.searchCollection.put(this.heap.get(biggest), i);
                this.heap.set(i, this.heap.get(biggest));
                this.heap.set(biggest, old);
                this.heapifyDown(biggest);
            }
        }

        private void heapifyUp(int i) {
            int parent = (i - 1) / 2;
            while (i > 0 && this.heap.get(i).compareTo(this.heap.get(parent)) > 0) {
                T old = this.heap.get(i);
                this.searchCollection.put(old, parent);
                this.searchCollection.put(this.heap.get(parent), i);
                this.heap.set(i, this.heap.get(parent));
                this.heap.set(parent, old);

                i = parent;
                parent = (i - 1) / 2;
            }
        }
    }
}
