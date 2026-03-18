package com.github.pathfind1103.problems.tbank.contests.contest4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TwoDSums {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[][] matrix = new long[N][M];
        long[][] prefixSums = new long[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long sums = 0;
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                sums += value;

                matrix[i][j] = value;
                prefixSums[i][j] = sums;
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) -1;

            long result = 0;

            for (int j = x1; j <= x2; j++) {
                long r = prefixSums[j][y2];
                long l = 0;

                if (y1 > 0) {
                    l = prefixSums[j][y1 - 1];
                }

                result += r - l;
            }

            out.println(result);
        }

        out.flush();
        out.close();
    }
}
