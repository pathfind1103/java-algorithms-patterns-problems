import com.github.pathfind1103.sorting.InsertionSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{}, arr, "Empty array should remain unchanged");
    }

    @Test
    void testSingleElementArray() {
        int[] arr = {1};
        InsertionSort.sort(arr);
        assertArrayEquals(new int[]{1}, arr, "Single element array should remain unchanged");
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        InsertionSort.sort(arr);
        assertArrayEquals(expected, arr, "Already sorted array should remain unchanged");
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        InsertionSort.sort(arr);
        assertArrayEquals(expected, arr, "Reverse sorted array should be sorted correctly");
    }

    @Test
    void testUnsortedArray() {
        int[] arr = {4, 2, 7, 1, 3};
        int[] expected = {1, 2, 3, 4, 7};
        InsertionSort.sort(arr);
        assertArrayEquals(expected, arr, "Unsorted array should be sorted correctly");
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {3, 1, 3, 2, 1};
        int[] expected = {1, 1, 2, 3, 3};
        InsertionSort.sort(arr);
        assertArrayEquals(expected, arr, "Array with duplicates should be sorted correctly");
    }

    @Test
    void testArrayWithNegativeNumbers() {
        int[] arr = {-3, 4, -1, 0, 2};
        int[] expected = {-3, -1, 0, 2, 4};
        InsertionSort.sort(arr);
        assertArrayEquals(expected, arr, "Array with negative numbers should be sorted correctly");
    }

    @Test
    void testNullArray() {
        int[] arr = null;
        assertDoesNotThrow(() -> InsertionSort.sort(arr), "Sorting null array should not throw an exception");
    }
}