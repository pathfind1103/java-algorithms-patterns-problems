package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.util.Scanner;

public class SquareRootAndSquareSquare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double request = scanner.nextDouble();
        double left = 0;
        double right = request;

        for (int i = 0; i < 80; i++) {
            double mid = left + (right - left) / 2;
            double calc = Math.pow(mid, 2) + Math.sqrt(mid + 1.0);

            if (request > calc) {
                left = mid;
            } else {
                right = mid;
            }
        }

        double answer = (left + right) / 2;
        System.out.println(answer);
        scanner.close();
    }
}
