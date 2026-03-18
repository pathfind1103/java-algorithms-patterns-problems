package com.github.pathfind1103.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    public static int[] sort(int[] array, int left, int right) {
        System.out.println("!");

        if (right <= left) {
            return array;
        }

        int q = array[(left + right) / 2];
        int i = left;
        int j = right;

        while (i <= j) {
            while (array[i] < q) {
                i++;
            }
            while (q < array[j]) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        sort(array, left, j);
        sort(array, i, right);

        return array;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[scanner.nextInt()];

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        int left = 0;
        int right = array.length - 1;

        System.out.println(Arrays.toString(sort(array, left, right)));
    }
}
