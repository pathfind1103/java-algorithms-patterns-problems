package com.github.pathfind1103.problems.tbank.contests.contest3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HeapSort {
    static long[] array;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(i, n);
        }

        sort(n);

        for (long number : array) {
            System.out.print(number + " ");
        }
    }

    static void sort(int n) {
        int size = n;

        while (size != 0) {
            long temp = array[0];
            array[0] = array[size - 1];
            array[size - 1] = temp;

            size--;

            siftDown(0, size);
        }
    }

    static void siftDown(int idx, int size) {
        while (true) {
            int leftChild = 2 * idx + 1;
            int rightChild = 2 * idx + 2;
            int largest = idx;

            if (leftChild < size && array[leftChild] > array[largest]) {
                largest = leftChild;
            }

            if (rightChild < size && array[rightChild] > array[largest]) {
                largest = rightChild;
            }

            if (largest == idx) {
                break;
            }

            long temp = array[idx];
            array[idx] = array[largest];
            array[largest] = temp;

            idx = largest;
        }
    }

}
