package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.util.Scanner;

public class TheRootOfTheCubicEquation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        double d = sc.nextDouble();

        double left  = -2000;
        double right =  2000;

        for (int iter = 0; iter < 100; iter++) {
            double mid = left + (right - left) / 2;

            double val = a * mid * mid * mid +
                    b * mid * mid +
                    c * mid +
                    d;

            if (a > 0) {
                if (val < 0) {
                    left = mid;
                } else {
                    right = mid;
                }
            } else {
                if (val > 0) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }

        double root = (left + right) / 2;

        System.out.println(root);

        sc.close();
    }
}