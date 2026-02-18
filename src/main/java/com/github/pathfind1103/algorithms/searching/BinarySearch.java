package com.github.pathfind1103.algorithms.searching;

import java.util.Scanner;

public class BinarySearch {
    public boolean binarySearch(int[] array, int target) {
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == array[mid]) {
                return true;
            }

            if (target > array[mid]) {
                start = mid + 1;
            } else  {
                end = mid - 1;
            }
        }

        return false;
    }
}