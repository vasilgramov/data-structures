package p05_linkedQueue;

import org.junit.Assert;
import org.junit.Test;

public class LinkedQueueTests {

    @Test(expected = IllegalArgumentException.class)
    public void dequeue_OnEmptyQueue_ShouldThrow() {
        LinkedQueueDemo<Integer> queue = new LinkedQueueDemo<>();

        queue.dequeue();
    }

    @Test
    public void dequeue_OnFewElements_ShouldReturnCorrectElement() {
        LinkedQueueDemo<Integer> queue = new LinkedQueueDemo<>();
        queue.enqueue(5);
        queue.enqueue(10);
        queue.enqueue(-3);

        int item = queue.dequeue();

        Assert.assertEquals(5, item);
    }

    @Test
    public void enqueue_OnEmptyQueue_ShouldIncreaseCount() {
        LinkedQueueDemo<Integer> queue = new LinkedQueueDemo<>();

        Assert.assertEquals(0, queue.size());

        queue.enqueue(5);

        Assert.assertEquals(1, queue.size());
    }

    @Test
    public void toArray_OnFewElements_ShouldReturnItemsInCorrectOrder() {
        LinkedQueueDemo<Integer> queue = new LinkedQueueDemo<>();

        queue.enqueue(5);
        queue.enqueue(-5);
        queue.enqueue(4);
        queue.enqueue(8);

        Object[] arr = queue.toArray();

        Assert.assertEquals(5, arr[0]);
        Assert.assertEquals(-5, arr[1]);
        Assert.assertEquals(4, arr[2]);
        Assert.assertEquals(8, arr[3]);
    }

    @Test
    public void toArray_OnEmptyQueue_ShouldReturnEmptyArray() {
        LinkedQueueDemo<Integer> queue = new LinkedQueueDemo<>();

        Object[] arr = queue.toArray();

        Assert.assertEquals(0, arr.length);
    }

}
