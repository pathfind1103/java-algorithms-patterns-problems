package com.github.pathfind1103.problems.tbank.contests.contest7;

import java.util.Arrays;
import java.util.Scanner;

public class RemovingBrackets {
    static int[][] dp;
    static int[][] split;
    static String s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;
        s = sc.next();
        int n = s.length();

        dp = new int[n][n];
        split = new int[n][n];


        for (int[] row : split) Arrays.fill(row, -1);

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (isPair(s.charAt(i), s.charAt(j))) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    split[i][j] = -1;
                }

                for (int k = i; k < j; k++) {
                    if (dp[i][k] + dp[k + 1][j] > dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                        split[i][j] = k;
                    }
                }
            }
        }

        printPath(0, n - 1);
        System.out.println();
    }

    static boolean isPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    static void printPath(int i, int j) {
        if (i > j || dp[i][j] == 0) return;

        if (split[i][j] == -1) {
            System.out.print(s.charAt(i));
            printPath(i + 1, j - 1);
            System.out.print(s.charAt(j));
        } else {
            int k = split[i][j];
            printPath(i, k);
            printPath(k + 1, j);
        }
    }
}
