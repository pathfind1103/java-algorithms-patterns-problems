package com.github.pathfind1103.problems.tbank.contests.contest9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AssignmentAdditionSum {
    static long[] tree;
    static long[] set; // Операции присваивания
    static long[] add; // Операции прибавления

    static long[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // Размер массива
        int m = Integer.parseInt(st.nextToken()); // Кол-во операций

        tree = new long[4 * n]; //Дерево отрезков
        set = new long[4 * n]; //Дерево обещания для присваивания
        Arrays.fill(set, -1);
        add = new long[4 * n]; //Дерево обещания для прибавления
        array = new long[n];
        Arrays.fill(array, 0);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken()); // Команда
            int l = Integer.parseInt(st.nextToken()); // l или l
            int r = Integer.parseInt(st.nextToken()); // r или r

            if (command < 3) {
                int v = Integer.parseInt(st.nextToken()); // v
                if (command == 1) {
                    assign(0, 0, n - 1, l, r - 1, v);
                } else {
                    plus(0, 0, n - 1, l, r - 1, v);
                }
            } else {
                System.out.println(sum(0, 0, n - 1, l, r - 1));
            }
        }
    }

    public static void plus(int v, int tl, int tr, int l, int r, int value) {
        if (l > r) return;

        if (tl == l && tr == r) {
            tree[v] += (long)value * (tr - tl + 1);
            add[v] += value;
            return;
        }

        push(v, tl, tr);

        int tm = (tl + tr) / 2;

        plus(v * 2 + 1, tl, tm, l, Math.min(r, tm), value);
        plus(v * 2 + 2, tm + 1, tr, Math.max(l, tm + 1), r, value);

        tree[v] = tree[2 * v + 1] + tree[2 * v + 2];

    }

    public static void assign(int v, int tl, int tr, int l, int r, int value) {
        if (l > r) return;

        if (tl == l && tr == r) {
            tree[v] = (long)value * (tr - tl + 1);
            set[v] = value;
            add[v] = 0;
            return;
        }

        push(v, tl, tr);

        int tm = (tl + tr) / 2;

        assign(v * 2 + 1, tl, tm, l, Math.min(r, tm), value);
        assign(v * 2 + 2, tm + 1, tr, Math.max(l, tm + 1), r, value);

        tree[v] = tree[2 * v + 1] + tree[2 * v + 2];
    }

    public static long sum(int v, int tl, int tr, int l, int r) {
        if (l > r) return 0;

        if (tl == l && tr == r) {
            return tree[v];
        }

        push(v, tl, tr);

        int tm = (tl + tr) / 2;

        return (sum(v * 2 + 1, tl, tm, l, Math.min(r, tm)) +
                sum(v * 2 + 2, tm + 1, tr, Math.max(l, tm + 1), r));

    }

    public static void push(int v, int tl, int tr) {
        if (tl == tr) return;
        int tm = (tl + tr) / 2;
        int left = 2 * v + 1;
        int right = 2 * v + 2;


        if (set[v] != -1) {
            long val = set[v];

            set[left] = set[right] = val;
            add[left] = add[right] = 0;

            tree[left] = val * (tm - tl + 1);
            tree[right] = val * (tr - tm);

            set[v] = -1;
        }

        if (add[v] != 0) {
            long val = add[v];

            add[left] += val;
            add[right] += val;


            tree[left] += val * (tm - tl + 1);
            tree[right] += val * (tr - tm);

            add[v] = 0;
        }
    }
}
