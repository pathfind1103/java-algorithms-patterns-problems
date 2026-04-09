package com.github.pathfind1103.problems.tbank.contests.contest7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KnightsMove {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][m + 1];
        dp[1][1] = 1;

        int[] moveN = {2, 2, 1, -1};
        int[] moveM = {-1, 1, 2, 2};

        for (int s = 2; s <= n + m; s++) {

            int startI = Math.min(s - 1, n);
            int endI = Math.max(1, s - m);

            for (int i = startI; i >= endI; i--) {
                int j = s - i;

                if (dp[i][j] == 0) continue;

                for (int x = 0; x < moveN.length; x++) {
                    if (i + moveN[x] >= 1 && i + moveN[x] <= n && j + moveM[x] >= 1 && j + moveM[x] <= m) {
                        dp[i + moveN[x]][j + moveM[x]] += dp[i][j];
                    }
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
