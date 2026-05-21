package com.github.pathfind1103.problems.tbank.contests.contest11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CuttingTheBeams {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();
        if (firstLine == null) return;

        StringTokenizer st = new StringTokenizer(firstLine);
        int l = Integer.parseInt(st.nextToken()); // Длина бруса
        int n = Integer.parseInt(st.nextToken()); // Количество распилов

        // Создаем массив координат, включая начало (0) и конец бруса (L)
        int[] a = new int[n + 2];
        a[0] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        a[n + 1] = l;

        int m = n + 2;
        int[][] dp = new int[m][m];

        for (int len = 2; len < m; len++) {
            for (int i = 0; i < m - len; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i + 1; k < j; k++) {
                    int currentCost = (a[j] - a[i]) + dp[i][k] + dp[k][j];
                    dp[i][j] = Math.min(dp[i][j], currentCost);
                }
            }
        }

        System.out.println(dp[0][m - 1]);
    }
}