package com.github.pathfind1103.problems.tbank.contests.exam;

import java.io.*;
import java.util.*;

public class PeteAndCMS {
    static long N;
    static int M;
    static long[] t;
    static long[] b;
    static long[] y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        t = new long[M];
        b = new long[M];
        y = new long[M];

        long right = Long.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Long.parseLong(st.nextToken());
            b[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());

            long fullBatches = (N - 1) / b[i];
            long rest = N - fullBatches * b[i];
            long timeForAll = fullBatches * (t[i] * b[i] + y[i]) + rest * t[i];
            right = Math.min(right, timeForAll);
        }

        long left = 0;
        long answer = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (can(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long[] result = new long[M];
        long remaining = N;

        for (int i = 0; i < M; i++) {
            long current = getCount(i, answer);
            result[i] = Math.min(current, remaining);
            remaining -= result[i];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append('\n');

        for (int i = 0; i < M; i++) {
            sb.append(result[i]);
            if (i + 1 < M) sb.append(' ');
        }

        System.out.println(sb);
    }

    public static boolean can(long time) {
        long total = 0;

        for (int i = 0; i < M; i++) {
            total += getCount(i, time);

            if (total >= N) {
                return true;
            }
        }

        return false;
    }

    public static long getCount(int i, long time) {
        long cycle = t[i] * b[i] + y[i];

        long fullCycles = time / cycle;
        long rem = time % cycle;

        return fullCycles * b[i] + Math.min(b[i], rem / t[i]);
    }
}

