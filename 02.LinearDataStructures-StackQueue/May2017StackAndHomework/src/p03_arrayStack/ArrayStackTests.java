package p03_arrayStack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ArrayStackTests {

    @Test
    public void pushPopShouldReturnCorrectCount() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        Assert.assertEquals(0, stack.size());

        stack.push(1);

        Assert.assertEquals(1, stack.size());

        Assert.assertEquals(Integer.valueOf(1), stack.pop());
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void pushPopThousandElementsShouldGrowStack() {
        ArrayStack<String> stack = new ArrayStack<>();

        Assert.assertEquals(0, stack.size());

        for (int i = 1; i <= 1000; i++) {
            stack.push(i + "");
            Assert.assertEquals(i, stack.size());
        }

        for (int i = 1000; i >= 1; i--) {
            Assert.assertEquals(i + "", stack.pop());
            Assert.assertEquals(i - 1, stack.size());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void popOnEmptyStackShouldThrow() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        stack.pop();
    }

    @Test
    public void pushPopWithInitialCapacityOneShouldGrowAndReturnCorrectCount() {
        ArrayStack<Integer> stack = new ArrayStack<>(1);

        Assert.assertEquals(0, stack.size());

        stack.push(3);

        Assert.assertEquals(1, stack.size());

        stack.push(6);

        Assert.assertEquals(2, stack.size());
        Assert.assertEquals(Integer.valueOf(6), stack.pop());
        Assert.assertEquals(1, stack.size());
        Assert.assertEquals(Integer.valueOf(3), stack.pop());
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void toArrayOnFewItemsShouldReturnElementsReversed() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        stack.push(3);
        stack.push(5);
        stack.push(-2);
        stack.push(7);

        Object[] arr = stack.toArray();

        Assert.assertEquals(Integer.valueOf(7), arr[0]);
        Assert.assertEquals(Integer.valueOf(-2), arr[1]);
        Assert.assertEquals(Integer.valueOf(5), arr[2]);
        Assert.assertEquals(Integer.valueOf(3), arr[3]);
    }

    @Test
    public void toArrayOnEmptyStackShouldReturnEmptyArray() {
        ArrayStack<Date> stack = new ArrayStack<Date>();
        Object[] arr = stack.toArray();

        Assert.assertEquals(0, arr.length);
    }
}
