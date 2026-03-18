package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            ask(mid);
            String answer = scanner.next();

            if (answer.equals(">=")) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("! " + left);
    }

    public static void ask(int value) {
        System.out.println(value);
        System.out.flush();
    }
}
