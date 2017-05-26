package p03_arrayStack;

public class Main {

    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        arrayStack.push(1);
        arrayStack.push(10);
        arrayStack.push(100);
        arrayStack.push(1000);
        arrayStack.push(10000);


        Object[] integers = arrayStack.toArray();
        for (int i = 0; i < integers.length; i++) {
            System.out.println(integers[i]);
        }
    }
}
