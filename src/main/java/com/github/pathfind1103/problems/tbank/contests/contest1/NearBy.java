package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.util.Scanner;

public class NearBy {
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
            int nearBy = nearBy(array, target);
            System.out.println(nearBy);
        }

    }

    public static int nearBy(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int mid = left + (right - left) / 2;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return array[mid];
            }

            if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long cand1 = (right >= 0) ? array[right] : Long.MAX_VALUE;
        long cand2 = (left < array.length) ? array[left] : Long.MAX_VALUE;

        long diff1 = Math.abs(cand1 - target);
        long diff2 = Math.abs(cand2 - target);

        if (diff1 < diff2) {
            return (int)cand1;
        }
        if (diff2 < diff1) {
            return (int)cand2;
        }

        return (int) Math.min(cand1, cand2);
    }
}
