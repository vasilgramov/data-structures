package p01_binaryHeap;

public class Startup {
    public static void main(String[] args) {

        Integer[] numbers = new Integer[] { 3, 7, 1, 5, 9, 8 };

        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(numbers);

        System.out.println("");
    }
}
