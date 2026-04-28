package com.github.pathfind1103.problems.tbank.contests.inter25042026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Stock {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line.trim());

        long[] values = new long[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            values[i] = Long.parseLong(st.nextToken());
        }

        if (n < 2) {
            System.out.println(0);
            return;
        }

        long[] first = new long[n + 1];
        long minPrice = values[1];

        for (int i = 2; i <= n; i++) {
            minPrice = Math.min(minPrice, values[i]);
            first[i] = Math.max(first[i - 1], values[i] - minPrice);
        }

        long[] second = new long[n + 2];
        long maxPrice = values[n];

        for (int i = n - 1; i >= 1; i--) {
            maxPrice = Math.max(maxPrice, values[i]);
            second[i] = Math.max(second[i + 1], maxPrice - values[i]);
        }

        long totalMax = 0;
        for (int i = 1; i <= n; i++) {
            totalMax = Math.max(totalMax, first[i] + second[i]);
        }

        System.out.println(totalMax);
    }
}

