package com.github.pathfind1103.problems.tbank.contests.contest7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PaidStairs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            System.out.print(values[0]);
            return;
        }

        int[] dp = new int[n];

        dp[0] = values[0];
        dp[1] = values[1];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + values[i];
        }

        System.out.print(dp[n - 1]);
    }
}
