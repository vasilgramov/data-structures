package p01;

import org.junit.Assert;
import org.junit.Test;

public class PerformanceGet {

    private static final int DEFAULT_CAPACITY = 100000;

    @Test
    public void PerformanceGet_WithExistingKeyWith100000Elements_ShouldReturnElementFast()
    {
        LimitedMemoryCollection<String, Integer> collection = new LimitedMemoryCollection<>(DEFAULT_CAPACITY);

        for (int i = 0; i < DEFAULT_CAPACITY; i++)
        {
            collection.set(i + "", i);
        }

        long start = System.currentTimeMillis();
        int item = collection.get("99999");
        long end = System.currentTimeMillis();
        long diff = end - start;
        Assert.assertTrue(diff <= 50);

        Assert.assertEquals(99999, item);
    }

    @Test
    public void PerformanceGet_WithExistingKeyWith80000Elements_ShouldReturnElementFast()
    {
        LimitedMemoryCollection<String, Integer> collection = new LimitedMemoryCollection<>(DEFAULT_CAPACITY);

        for (int i = 1; i <= 80000; i++)
        {
            collection.set(i + "", DEFAULT_CAPACITY - i);
        }

        long start = System.currentTimeMillis();
        int item = collection.get("75000");
        long end = System.currentTimeMillis();
        long diff = end - start;
        Assert.assertTrue(diff <= 50);

        Assert.assertEquals(DEFAULT_CAPACITY - 75000, item);
    }

    @Test
    public void PerformanceGet_WithNonExistingKeyWith100000Elements_ShouldReturnKeyNotFoundFast()
    {
        LimitedMemoryCollection<String, Integer> collection = new LimitedMemoryCollection<>(DEFAULT_CAPACITY);

        for (int i = 0; i < DEFAULT_CAPACITY; i++)
        {
            collection.set(i + "", i);
        }

        long start = System.currentTimeMillis();
        try
        {
            collection.get("100001");
            Assert.fail();
        }
        catch (IllegalArgumentException ex)
        {
            //Expected
            long end = System.currentTimeMillis();
            long diff = end - start;
            Assert.assertTrue(diff <= 50);
        }
    }

    @Test
    public void PerformanceGet_With100000GetCalls()
    {
        LimitedMemoryCollection<String, Integer> collection = new LimitedMemoryCollection<>(DEFAULT_CAPACITY);

        for (int i = 1; i <= DEFAULT_CAPACITY; i++)
        {
            collection.set(i + "", i);
        }

        long start = System.currentTimeMillis();
        for (int i = 1; i <= DEFAULT_CAPACITY; i++)
        {
            Assert.assertEquals(i, (int) collection.get(i + ""));
        }
        long end = System.currentTimeMillis();
        long diff = end - start;
        Assert.assertTrue(diff <= 350);
    }
}
