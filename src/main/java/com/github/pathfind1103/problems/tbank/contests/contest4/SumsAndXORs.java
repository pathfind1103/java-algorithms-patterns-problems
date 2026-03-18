package com.github.pathfind1103.problems.tbank.contests.contest4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumsAndXORs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PrintWriter printWriter = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());

        int[] array = new int[n];
        long[] prefixSums = new long[n];
        long sums = 0;
        long x = 0;
        long[] xor = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            array[i] = value;

            sums += value;
            prefixSums[i] = sums;

            x ^= value;
            xor[i] = x;
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;

            if (command == 1) {
                long right = prefixSums[r];
                long left;

                if (l > 0) {
                    left = prefixSums[l - 1];
                } else {
                    left = 0;
                }

                long result = right - left;
                printWriter.println(result);
            }

            if (command == 2) {
                long right = xor[r];
                long left;

                if (l > 0) {
                    left = xor[l - 1];
                } else {
                    left = 0;
                }

                long result = right ^ left;
                printWriter.println(result);
            }
        }

        printWriter.flush();
        printWriter.close();
    }
}
