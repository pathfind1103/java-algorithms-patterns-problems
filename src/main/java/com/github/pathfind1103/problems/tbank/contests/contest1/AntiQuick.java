package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.io.*;

public class AntiQuick {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] result = new int[n];
        int[] p = new int[n];

        for (int i = 0; i < n; i++) p[i] = i;

        for (int i = 0; i < n; i++) {
            int pivotIndex = (i + n - 1) / 2;

            result[p[pivotIndex]] = i + 1;

            int tmp = p[pivotIndex];
            p[pivotIndex] = p[i];
            p[i] = tmp;
        }

        StringBuilder out = new StringBuilder();
        for (int x : result) out.append(x).append(' ');
        System.out.println(out);
    }
}