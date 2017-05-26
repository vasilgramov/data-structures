package p04_linkedStack;

/**
 * Created by vladix on 5/25/17.
 */
public class Main {

    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        linkedStack.push(1);
        linkedStack.push(10);
        linkedStack.push(100);
        linkedStack.push(1000);
        linkedStack.push(10000);

        Object[] integers = linkedStack.toArray();
        for (Object integer : integers) {
            System.out.println(integer);
        }


        Object[] integers1 = linkedStack.toArray();
        for (Object integer : integers1) {
            System.out.println(integer);
        }

    }
}
