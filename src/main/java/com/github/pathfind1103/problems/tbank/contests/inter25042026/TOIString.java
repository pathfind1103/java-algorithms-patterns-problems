package com.github.pathfind1103.problems.tbank.contests.inter25042026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TOIString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        String s = br.readLine();
        if (s == null) s = "";

        if (n == 0 || s.isEmpty()) {
            System.out.println(0);
            return;
        }

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = isTOI(s.charAt(i)) ? 0 : 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                char left = s.charAt(i);
                char right = s.charAt(j);

                if (isTOI(left) && isTOI(right) && left == right) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    int res = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);

                    int cost;
                    if (isTOI(left) && isTOI(right)) cost = 1;
                    else if (isTOI(left) || isTOI(right)) cost = 1;
                    else cost = 2;

                    dp[i][j] = Math.min(res, dp[i + 1][j - 1] + cost);
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }

    private static boolean isTOI(char c) {
        return c == 'T' || c == 'O' || c == 'I';
    }
}
