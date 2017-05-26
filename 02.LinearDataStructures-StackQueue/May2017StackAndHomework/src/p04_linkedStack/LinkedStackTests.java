package p04_linkedStack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class LinkedStackTests {

    @Test
    public void pushPopShouldReturnCorrectCount() {
        LinkedStack<Integer> stack = new LinkedStack<>();

        Assert.assertEquals(0, stack.size());

        stack.push(1);

        Assert.assertEquals(1, stack.size());

        Assert.assertEquals(Integer.valueOf(1), stack.pop());
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void pushPopThousandElementsShouldGrowStack() {
        LinkedStack<String> stack = new LinkedStack<>();

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

    @Test
    public void toArrayNFewItemsShouldReturnElementsReversed() {
        LinkedStack<Integer> stack = new LinkedStack<>();

        stack.push(3);
        stack.push(5);
        stack.push(-2);
        stack.push(7);

        Object[] arr = stack.toArray();

        Assert.assertEquals(7, arr[0]);
        Assert.assertEquals(-2, arr[1]);
        Assert.assertEquals(5, arr[2]);
        Assert.assertEquals(3, arr[3]);
    }

    @Test
    public void toArrayOnEmptyStackShouldReturnEmptyArray() {
        LinkedStack<Date> stack = new LinkedStack<Date>();
        Object[] arr = stack.toArray();

        Assert.assertEquals(0, arr.length);
    }

}
