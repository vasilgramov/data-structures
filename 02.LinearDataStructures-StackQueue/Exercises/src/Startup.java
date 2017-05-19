public class Startup {

    public static void main(String[] args) {
        CircularQueue<String> circularQueue = new CircularQueue<>(2); // by default initial capacity 16

        circularQueue.enqueue("1");
        circularQueue.enqueue("2");
        circularQueue.enqueue("3");
        circularQueue.enqueue("4");
        circularQueue.enqueue("5");

        System.out.println("-------------------------------------------");

        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.dequeue());
        System.out.println("-------------------------------------------");


        System.out.println("-------------------------------------------");

        System.out.println(circularQueue.dequeue());
        System.out.println("----------------------------");
        circularQueue.enqueue("4");
        circularQueue.enqueue("5");

        System.out.println("-------------------------------------------");

        Object[] queueToArray = circularQueue.toArray(); // returns array of objects :(

        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");

        for (int i = 0; i < queueToArray.length; i++) {
            System.out.println(queueToArray[i]);
        }

    }
}
