package com.github.pathfind1103.problems.problem1;

public class Problem1 {
    static int[] arr1 = {1, 2, 5, 8};
    static int[] arr2 = {3, 4, 7, 9};

    public static void main(String[] args) {
        boolean flag = false;
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                flag = true;
                break;
            } else if (arr1[i] > arr2[j]) {
                j += 1;
            } else {
                i += 1;
            }
        }

        System.out.println(flag);
    }
}
