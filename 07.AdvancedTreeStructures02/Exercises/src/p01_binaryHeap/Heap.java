package p01_binaryHeap;

public class Heap {

    public static <T extends Comparable<T>> void sort(T[] arr) {
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--)
        {
            down(i, n, arr);
        }

        for (int i = n - 1; i > 0; i--)
        {
            swap(0, i, arr);
            down(0, i, arr);
        }
    }

    private static <T extends Comparable<T>> void down(int current, int border, T[] arr)
    {
        while (current < border / 2)
        {
            int child = 2 * current + 1;
            if (child + 1 < border && isLess(child, child + 1, arr))
            {
                child++;
            }

            if (isLess(child, current, arr))
            {
                break;
            }

            swap(current, child, arr);
            current = child;
        }
    }

    private static <T extends Comparable<T>> boolean isLess(int a, int b, T[] arr) {
        return arr[a].compareTo(arr[b]) < 0;
    }

    private static <T> void swap(int a, int b, T[] arr) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
