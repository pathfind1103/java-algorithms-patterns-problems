package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.util.Arrays;
import java.util.Scanner;

public class WhatYesTheBubble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] array = new int[n];

        int indexOfRightZero = n - 1;
        int output = 0;
        System.out.println(1);

        for (int i = 0; i < n; i++) {
            int indexOfNewOne = scanner.nextInt();
            int pos = indexOfNewOne - 1;
            array[pos] = 1;

            if (pos < indexOfRightZero) {
                output += 1;
            } else if (pos == indexOfRightZero) {
                indexOfRightZero--;

                while (indexOfRightZero >= 0 && array[indexOfRightZero] == 1) {
                    output -= 1;
                    indexOfRightZero -= 1;
                }
            }

            int answer;
            if (indexOfRightZero < 0) {
                answer = 1;
            } else {
                answer = 1 + output;
            }

            System.out.println(answer);
        }
    }
}
