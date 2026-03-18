package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.util.*;

public class MergeSortingWithAJoke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        MergeResult result = sort(new MergeResult(array, 0));
        System.out.println(result.getInversions());

        for (int i : result.getSorted()) {
            System.out.print(i + " ");
        }

        scanner.close();
    }

    public static MergeResult sort(MergeResult mergeResult) {
        int [] arr = mergeResult.getSorted();

        if (Arrays.equals(arr, null)) {
            return new MergeResult(arr, 0);
        }

        int n = arr.length;
        if (n <= 1) {
            return new MergeResult(arr, 0);
        }

        int[] leftHalf = Arrays.copyOfRange(arr, 0, (n/2));
        int[] rightHalf = Arrays.copyOfRange(arr, n/2, n);

        MergeResult left = sort(new MergeResult(leftHalf, 0));
        MergeResult right = sort(new MergeResult(rightHalf, 0));

        MergeResult merged = merge(left.getSorted(), right.getSorted());

        long totalInversions = left.getInversions() + right.getInversions() + merged.inversions;

        return new MergeResult(merged.getSorted(), totalInversions);
    }

    public static MergeResult merge(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        int counter = 0;

        int[] c = new int[a.length + b.length];
        while (i < a.length || j < b.length) {
            if (j == b.length || i < a.length && a[i] < b[j]) {
                c[i + j] = a[i];
                i++;
            } else {
                counter += (a.length - i);
                c[i + j] = b[j];
                j++;
            }
        }

        return new MergeResult(c, counter);
    }

    static class MergeResult {
        int[] sorted;
        long inversions;

        MergeResult(int[] sorted, long inversions) {
            this.sorted = sorted;
            this.inversions = inversions;
        }

        @Override
        public String toString() {
            return "MergeResult{" +
                    "sorted=" + Arrays.toString(sorted) +
                    ", inversions=" + inversions +
                    '}';
        }

        public int[] getSorted() {
            return sorted;
        }

        public void setSorted(int[] sorted) {
            this.sorted = sorted;
        }

        public long getInversions() {
            return inversions;
        }

        public void setInversions(long inversions) {
            this.inversions = inversions;
        }
    }
}
