import com.github.pathfind1103.sorting.MergeSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        int[] result = MergeSort.sort(arr);
        assertArrayEquals(new int[]{}, result, "Empty array should return empty array");
    }

    @Test
    void testSingleElementArray() {
        int[] arr = {1};
        int[] result = MergeSort.sort(arr);
        assertArrayEquals(new int[]{1}, result, "Single element array should return unchanged");
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        int[] result = MergeSort.sort(arr);
        assertArrayEquals(expected, result, "Already sorted array should return unchanged");
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        int[] result = MergeSort.sort(arr);
        assertArrayEquals(expected, result, "Reverse sorted array should be sorted correctly");
    }

    @Test
    void testUnsortedArray() {
        int[] arr = {4, 2, 7, 1, 3};
        int[] expected = {1, 2, 3, 4, 7};
        int[] result = MergeSort.sort(arr);
        assertArrayEquals(expected, result, "Unsorted array should be sorted correctly");
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {3, 1, 3, 2, 1};
        int[] expected = {1, 1, 2, 3, 3};
        int[] result = MergeSort.sort(arr);
        assertArrayEquals(expected, result, "Array with duplicates should be sorted correctly");
    }

    @Test
    void testArrayWithNegativeNumbers() {
        int[] arr = {-3, 4, -1, 0, 2};
        int[] expected = {-3, -1, 0, 2, 4};
        int[] result = MergeSort.sort(arr);
        assertArrayEquals(expected, result, "Array with negative numbers should be sorted correctly");
    }
}

