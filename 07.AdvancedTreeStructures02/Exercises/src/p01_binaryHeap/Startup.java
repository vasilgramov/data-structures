package p01_binaryHeap;

public class Startup {
    public static void main(String[] args) {

        Integer[] numbers = new Integer[] { 17, 3, 19, 36, 25, 1, 2, 7, 100 };

        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(numbers);
        binaryHeap.add(123);

        while (binaryHeap.count() > 0) {
            System.out.println(binaryHeap.peek());
            binaryHeap.extractMax();
        }
    }
}
