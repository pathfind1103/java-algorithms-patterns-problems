package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] array = new int[n];

        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        scanner.nextLine();
        for (int i = 0; i < k; i++) {
            int target = scanner.nextInt();
            System.out.println(binarySearch(array, target) ? "YES" : "NO");
        }


    }

    public static boolean binarySearch(int[] array, int target) {
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