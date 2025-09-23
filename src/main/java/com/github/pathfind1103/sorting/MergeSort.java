package com.github.pathfind1103.sorting;

import java.util.Arrays;

public class MergeSort {
    public static int[] sort(int[] arr) {

        if (Arrays.equals(arr, null)) return arr;

        int n = arr.length;
        if (n <= 1) {
            return arr;
        }

        int[] leftHalf = Arrays.copyOfRange(arr, 0, (n/2));
        int[] rightHalf = Arrays.copyOfRange(arr, n/2, n);

        leftHalf = sort(leftHalf);
        rightHalf = sort(rightHalf);

        return merge(leftHalf, rightHalf);
    }

    public static int[] merge (int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int[] c = new int[a.length + b.length];
        while (i < a.length || j < b.length) {
            if (j == b.length || i < a.length && a[i] < b[j]) {
                c[i + j] = a[i];
                i++;
            } else {
                c[i + j] = b[j];
                j++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4 ,1, 7};
        System.out.println(Arrays.toString(sort(arr)));
    }
}
