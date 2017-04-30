import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vladix on 4/30/17.
 */
public class MaxHeapTests {

    @Test
    public void buildHeap_ExtractAllElements_ShouldReturnElementsSorted() {
        // Arrange
        int[] arr = new int[] { 3, 4, -1, 15, 2, 77, -3, 4, 12 };

        // Act
        MaxHeap.PriorityQueue<Integer> heap = new MaxHeap.PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.enqueue(arr[i]);
        }

        int[] elements = new int[arr.length];
        int index = 0;
        while (heap.size() > 0) {
            int maxElement = heap.extractMax();
            elements[index++] = maxElement;
        }

        int[] expected = new int[] { 77, 15, 12, 4, 4, 3, 2, -1, -3 };
        System.out.println(Arrays.toString(elements));
        Assert.assertArrayEquals(expected, elements);
    }

    @Test
    public void emptyHeap_InsertElements_ExtractAllElements_ShouldReturnElementsSorted() {
        // Arrange
        int[] arr = new int[] { 3, 4, -1, 15, 2, 77, -3, 4, 12 };

        // Act
        MaxHeap.PriorityQueue<Integer> heap = new MaxHeap.PriorityQueue<>();

        for (int num : arr) {
            heap.enqueue(num);
        }

        int[] elements = new int[arr.length];
        int index = 0;
        while (heap.size() > 0) {
            int maxElement = heap.extractMax();
            elements[index++] = maxElement;
        }

        // Assert
        int[] expected = new int[] { 77, 15, 12, 4, 4, 3, 2, -1, -3 };
        Assert.assertArrayEquals(expected, elements);
    }

    @Test
    public void test_MaxHeap_Sorting100Elements() {
        int[] arr = new int[] {
                25, 55, 54, 38, 5381, 93, 19, 33, 3177, 69, 85, 2, 6948, 33, 35, 31, 1470, 46, 52, 90, 6212, 72, 74, 90, 7393, 5, 65, 58, 1689, 74, 80, 86, 8381, 29, 87, 95, 498, 85, 84, 66, 6184, 76, 92, 58, 6154, 16, 61, 24, 942, 79, 49, 59, 8336, 77, 20, 89, 5548, 100, 35, 35, 4029, 70, 87, 62, 7053, 40, 35, 10, 5210, 80, 29, 40, 5514, 96, 8, 68, 428, 58, 100, 40, 6
        };

        int[] expected = new int[] {
                8381, 8336, 7393, 7053, 6948, 6212, 6184, 6154, 5548, 5514, 5381, 5210, 4029, 3177, 1689, 1470, 942, 498, 428, 100, 100, 96, 95, 93, 92, 90, 90, 89, 87, 87, 86, 85, 85, 84, 80, 80, 79, 77, 76, 74, 74, 72, 70, 69, 68, 66, 65, 62, 61, 59, 58, 58, 58, 55, 54, 52, 49, 46, 40, 40, 40, 38, 35, 35, 35, 35, 33, 33, 31, 29, 29, 25, 24, 20, 19, 16, 10, 8, 6, 5, 2
        };

        MaxHeap.PriorityQueue<Integer> heap = new MaxHeap.PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.enqueue(arr[i]);
        }

        int[] result = new int[expected.length];
        int index = 0;
        while (heap.size() > 0) {
            int maxElement = heap.extractMax();
            result[index++] = maxElement;
        }

        Assert.assertArrayEquals(expected, result);
    }
}
