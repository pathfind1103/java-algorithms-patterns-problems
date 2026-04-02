package com.github.pathfind1103.problems.tbank.contests.contest6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Offline {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        int[][] dist = new int[n + 1][n + 1];

        for (int[] row : dist) {
            Arrays.fill(row, 1_000_000_000);
        }

        for (int i = 0; i <= n; i++) {
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            dist[city1][city2] = distance;
            dist[city2][city1] = distance;
        }

        // Алгоритм Флойда-Уоршелла
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int minMaxDist = Integer.MAX_VALUE;
        int centerNode = -1;

        for (int i = 1; i <= n; i++) {
            int currentMax = 0;

            for (int j = 1; j <= n; j++) {
                if (dist[i][j] != 1_000_000_000) {
                    currentMax = Math.max(currentMax, dist[i][j]);
                }
            }

            if (currentMax < minMaxDist) {
                minMaxDist = currentMax;
                centerNode = i;
            }
        }

        System.out.print(centerNode);
    }
}
