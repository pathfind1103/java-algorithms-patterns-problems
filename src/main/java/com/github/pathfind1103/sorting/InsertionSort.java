package com.github.pathfind1103.sorting;

import java.util.Arrays;

public class InsertionSort {
    public static void sort(int[] arr) {
        if (arr == null) return;
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                  int temp = arr[j];
                  arr[j] = arr[j - 1];
                  arr[j - 1] = temp;
                  j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 7};
        sort(arr);
        System.out.println("Sorted: " + Arrays.toString(arr));;
    }
}
