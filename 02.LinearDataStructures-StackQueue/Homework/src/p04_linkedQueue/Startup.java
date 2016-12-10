package p04_linkedQueue;

public class Startup {
    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();

        System.out.println("beofere " + linkedQueue.getCount());
        for (int i = 0; i < 10; i++) {

            linkedQueue.enqueue(i * 12);
            System.out.println("after " + linkedQueue.getCount());
        }
    }
}
