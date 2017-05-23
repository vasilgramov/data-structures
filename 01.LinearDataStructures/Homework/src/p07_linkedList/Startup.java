package p07_linkedList;

public class Startup {
    public static void main(String[] args) {
        LinkedListv2<Integer> linkedList = new LinkedListv2<>();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(5);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        System.out.println(linkedList.lastIndexOf(10));

    }
}
