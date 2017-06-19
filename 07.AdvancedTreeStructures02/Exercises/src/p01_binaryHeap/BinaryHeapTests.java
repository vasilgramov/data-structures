package p01_binaryHeap;

import org.junit.Assert;
import org.junit.Test;

public class BinaryHeapTests {

    @Test
    public void insert_Single_TestCount() {
        // Arrange
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        // Act
        heap.add(3);

        // Assert
        Assert.assertEquals(1, heap.size());
    }

    @Test
    public void insert_Single_TestPeek() {
        // Arrange
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        // Act
        heap.add(3);

        // Assert
        Assert.assertEquals(Integer.valueOf(3), heap.peek());
    }

    @Test
    public void insert_Multiple_TestCount() {
        // Arrange
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        // Act
        // Assert
        heap.add(3);
        Assert.assertEquals(1, heap.size());

        heap.add(5);
        Assert.assertEquals(2, heap.size());

        heap.add(6);
        Assert.assertEquals(3, heap.size());
    }

    @Test
    public void insert_Multiple_TestPeek() {
        // Arrange
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        // Act
        // Assert
        heap.add(3);
        Assert.assertEquals(Integer.valueOf(3), heap.peek());

        heap.add(5);
        Assert.assertEquals(Integer.valueOf(5), heap.peek());

        heap.add(2);
        Assert.assertEquals(Integer.valueOf(5), heap.peek());

        heap.add(1);
        Assert.assertEquals(Integer.valueOf(5), heap.peek());

        heap.add(7);
        Assert.assertEquals(Integer.valueOf(7), heap.peek());

        heap.add(8);
        Assert.assertEquals(Integer.valueOf(8), heap.peek());
    }

    @Test
    public void pull_Single_TestCount() {
        // Arrange
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        // Act
        heap.add(3);
        heap.add(5);

        heap.extractMax();

        // Assert
        Assert.assertEquals(1, heap.size());
    }

    @Test
    public void pull_Single_TestElement() {
        // Arrange
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        // Act
        heap.add(3);

        // Assert
        Assert.assertEquals(Integer.valueOf(3), heap.extractMax());
    }

    @Test
    public void pull_Multiple_TestCount() {
        // Arrange
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        // Act
        heap.add(5);
        heap.add(3);
        heap.add(1);

        // Assert
        Assert.assertEquals(Integer.valueOf(5), heap.extractMax());
        Assert.assertEquals(Integer.valueOf(3), heap.extractMax());
        Assert.assertEquals(Integer.valueOf(1), heap.extractMax());
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void pull_Multiple_TestElements() {
        // Arrange
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        // Act
        heap.add(3);
        heap.add(5);
        heap.add(6);
        heap.add(7);

        // Assert
        Assert.assertEquals(Integer.valueOf(7), heap.extractMax());
        Assert.assertEquals(Integer.valueOf(6), heap.extractMax());
        Assert.assertEquals(Integer.valueOf(5), heap.extractMax());
        Assert.assertEquals(Integer.valueOf(3), heap.extractMax());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Pull_EmptyHeap() {
        // Arrange
        BinaryHeap<Integer> heap = new BinaryHeap<>();

        // Act
        heap.extractMax();
    }
}
