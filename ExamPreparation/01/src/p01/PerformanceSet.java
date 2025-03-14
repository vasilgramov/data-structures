package p01;

import org.junit.Assert;
import org.junit.Test;

public class PerformanceSet {
    @Test
    public void PerformanceSet_With100000CallsOnExistingElements()
    {
        LimitedMemoryCollection<String,Integer> collection = new LimitedMemoryCollection<>(100000);

        for (int i = 1; i <= 100000; i++) {
            collection.set(i+"",i);
        }

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 100000; i++) {
            collection.set(i+"", 100000 - i);
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 200);

        for (int i = 1; i < 100000; i++) {
            Assert.assertEquals("Expected Value did not match!",(Integer)(100000 - i),collection.get(i+""));
        }
    }

    @Test
    public void PerformanceSet_Called100000Times()
    {
        LimitedMemoryCollection<String,Integer> collection = new LimitedMemoryCollection<>(100000);

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 100000; i++) {
            collection.set(i + "", i);
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 200);

        for (int i = 1; i <= 100000; i++) {
            collection.set(i + "", 100000 - i);
        }

        for (int i = 1; i < 100000; i++) {
            Assert.assertEquals("Expected Value did not match!", (Integer) (100000 - i), collection.get(i + ""));
        }
    }

    @Test
    public void PerformanceSet_With200000ElementsWith100000Capacity()
    {
        LimitedMemoryCollection<String,Integer> collection = new LimitedMemoryCollection<>(100000);

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 200000; i++) {
            collection.set(i + "", i);
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 500);
    }
}
