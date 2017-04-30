import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by vladix on 4/30/17.
 */
public class MinHeapTests {

    @Test
    public void buildHeap_ExtractAllElements_ShouldReturnElementsSorted() {
        // Arrange
        int[] arr = new int[] { 3, 4, -1, 15, 2, 77, -3, 4, 12 };

        // Act
        MinHeap.PriorityQueue<Integer> heap = new MinHeap.PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.enqueue(arr[i]);
        }

        int[] elements = new int[arr.length];
        int index = 0;
        while (heap.size() > 0) {
            int maxElement = heap.extractMin();
            elements[index++] = maxElement;
        }

        int[] expected = new int[] { -3, -1, 2, 3, 4, 4, 12, 15, 77 };
        Assert.assertArrayEquals(expected, elements);
    }

    @Test
    public void emptyHeap_InsertElements_ExtractAllElements_ShouldReturnElementsSorted() {
        // Arrange
        int[] arr = new int[] { 3, 4, -1, 15, 2, 77, -3, 4, 12 };
        // Act
        MinHeap.PriorityQueue<Integer> heap = new MinHeap.PriorityQueue<>();

        for (int num : arr) {
            heap.enqueue(num);
        }

        int[] elements = new int[arr.length];
        int index = 0;
        while (heap.size() > 0) {
            int maxElement = heap.extractMin();
            elements[index++] = maxElement;
        }

        // Assert
        int[] expected = new int[] { -3, -1, 2, 3, 4, 4, 12, 15, 77 };
        Assert.assertArrayEquals(expected, elements);
    }

    @Test
    public void test_MinHeap_Sorting100Elements() {
        int[] arr = new int[] {
                25, 55, 54, 38, 5381, 93, 19, 33, 3177, 69, 85, 2, 6948, 33, 35, 31, 1470, 46, 52, 90, 6212, 72, 74, 90, 7393, 5, 65, 58, 1689, 74, 80, 86, 8381, 29, 87, 95, 498, 85, 84, 66, 6184, 76, 92, 58, 6154, 16, 61, 24, 942, 79, 49, 59, 8336, 77, 20, 89, 5548, 100, 35, 35, 4029, 70, 87, 62, 7053, 40, 35, 10, 5210, 80, 29, 40, 5514, 96, 8, 68, 428, 58, 100, 40, 6
        };

        int[] expected = new int[] {
                2, 5, 6, 8, 10, 16, 19, 20, 24, 25, 29, 29, 31, 33, 33, 35, 35, 35, 35, 38, 40, 40, 40, 46, 49, 52, 54, 55, 58, 58, 58, 59, 61, 62, 65, 66, 68, 69, 70, 72, 74, 74, 76, 77, 79, 80, 80, 84, 85, 85, 86, 87, 87, 89, 90, 90, 92, 93, 95, 96, 100, 100, 428, 498, 942, 1470, 1689, 3177, 4029, 5210, 5381, 5514, 5548, 6154, 6184, 6212, 6948, 7053, 7393, 8336, 8381
        };

        MinHeap.PriorityQueue<Integer> heap = new MinHeap.PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.enqueue(arr[i]);
        }

        int[] result = new int[expected.length];
        int index = 0;
        while (heap.size() > 0) {
            int maxElement = heap.extractMin();
            result[index++] = maxElement;
        }

        Assert.assertArrayEquals(expected, result);
    }
}
