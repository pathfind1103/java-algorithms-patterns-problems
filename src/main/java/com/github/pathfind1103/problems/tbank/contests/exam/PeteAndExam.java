package com.github.pathfind1103.problems.tbank.contests.exam;

import java.io.*;
import java.util.*;

public class PeteAndExam {
    static final int LOG = 21;

    static int n;
    static int[][] tree;
    static int[] promises;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        n = Integer.parseInt(br.readLine());

        int[] array = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        tree = new int[4 * n][LOG];
        promises = new int[4 * n];

        build(0, 0, n - 1, array);

        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;

            if (command == 1) {
                out.println(sum(0, 0, n - 1, l, r));
            } else {
                int x = Integer.parseInt(st.nextToken());
                update(0, 0, n - 1, l, r, x);
            }
        }

        out.flush();
        out.close();
    }

    public static void build(int v, int tl, int tr, int[] array) {
        if (tl == tr) {
            for (int bit = 0; bit < LOG; bit++) {
                if (((array[tl] >> bit) & 1) == 1) {
                    tree[v][bit] = 1;
                }
            }
            return;
        }

        int tm = (tl + tr) / 2;

        build(2 * v + 1, tl, tm, array);
        build(2 * v + 2, tm + 1, tr, array);

        for (int bit = 0; bit < LOG; bit++) {
            tree[v][bit] = tree[2 * v + 1][bit] + tree[2 * v + 2][bit];
        }
    }

    public static void update(int v, int tl, int tr, int l, int r, int x) {
        if (l > r) return;

        if (tl == l && tr == r) {
            apply(v, tl, tr, x);
            return;
        }

        push(v, tl, tr);

        int tm = (tl + tr) / 2;

        update(2 * v + 1, tl, tm, l, Math.min(r, tm), x);
        update(2 * v + 2, tm + 1, tr, Math.max(l, tm + 1), r, x);

        for (int bit = 0; bit < LOG; bit++) {
            tree[v][bit] = tree[2 * v + 1][bit] + tree[2 * v + 2][bit];
        }
    }

    public static long sum(int v, int tl, int tr, int l, int r) {
        if (l > r) return 0;

        if (tl == l && tr == r) {
            return getSum(v);
        }

        push(v, tl, tr);

        int tm = (tl + tr) / 2;

        return sum(2 * v + 1, tl, tm, l, Math.min(r, tm))
                + sum(2 * v + 2, tm + 1, tr, Math.max(l, tm + 1), r);
    }

    public static void apply(int v, int tl, int tr, int x) {
        int len = tr - tl + 1;

        for (int bit = 0; bit < LOG; bit++) {
            if (((x >> bit) & 1) == 1) {
                tree[v][bit] = len - tree[v][bit];
            }
        }

        promises[v] ^= x;
    }

    public static void push(int v, int tl, int tr) {
        if (promises[v] == 0 || tl == tr) return;

        int tm = (tl + tr) / 2;
        int x = promises[v];

        apply(2 * v + 1, tl, tm, x);
        apply(2 * v + 2, tm + 1, tr, x);

        promises[v] = 0;
    }

    public static long getSum(int v) {
        long result = 0;

        for (int bit = 0; bit < LOG; bit++) {
            result += (long) tree[v][bit] * (1L << bit);
        }

        return result;
    }
}