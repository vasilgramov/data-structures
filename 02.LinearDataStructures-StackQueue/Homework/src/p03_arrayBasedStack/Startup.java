package p03_arrayBasedStack;

public class Startup {
    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(1);

        arrayStack.push(123);
        System.out.println(arrayStack.pop());

        arrayStack.push(1);

        arrayStack.push(6);
        arrayStack.push(5);

        System.out.println(arrayStack.size());
        System.out.println(arrayStack.pop());
        System.out.println("//----------------------------------");
        System.out.println(arrayStack.size());
        System.out.println(arrayStack.pop());

        System.out.println("//----------------------------------");
        System.out.println(arrayStack.peek());
    }
}
