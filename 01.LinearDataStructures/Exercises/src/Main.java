public class Main {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addFirst(1);
        doublyLinkedList.addLast(2);
        doublyLinkedList.addFirst(0);
        doublyLinkedList.addLast(5);


        System.out.println(doublyLinkedList.removeFirst());
        System.out.println("-----------------------------");
        for (int node : doublyLinkedList) {
            System.out.println(node);
        }
        System.out.println("-----------------------------");
        System.out.println(doublyLinkedList.removeLast());
        System.out.println("-----------------------------");
        for (int node : doublyLinkedList) {
            System.out.println(node);
        }
    }
}
