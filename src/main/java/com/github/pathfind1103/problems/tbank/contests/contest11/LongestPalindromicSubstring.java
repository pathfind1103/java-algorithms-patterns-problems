package com.github.pathfind1103.problems.tbank.contests.contest11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestPalindromicSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        if (s == null || s.isEmpty()) return;

        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        char[] result = new char[dp[0][n - 1]];
        int leftIdx = 0;
        int rightIdx = result.length - 1;

        int i = 0, j = n - 1;
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                result[leftIdx] = s.charAt(i);
                result[rightIdx] = s.charAt(i);
                leftIdx++;
                rightIdx--;
                i++;
                j--;
            } else if (dp[i + 1][j] >= dp[i][j - 1]) {
                i++;
            } else {
                j--;
            }
        }

        System.out.println(dp[0][n - 1]);
        System.out.println(new String(result));
    }
}