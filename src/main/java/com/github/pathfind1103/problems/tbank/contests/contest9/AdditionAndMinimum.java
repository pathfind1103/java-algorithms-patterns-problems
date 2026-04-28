package com.github.pathfind1103.problems.tbank.contests.contest9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AdditionAndMinimum {
    static long[] tree;
    static long[] promises;

    static long[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // Размер массива
        int m = Integer.parseInt(st.nextToken()); // Кол-во операций

        tree = new long[4 * n]; //Дерево отрезков
        promises = new long[4 * n];
        array = new long[n];
        Arrays.fill(array, 0);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken()); // Команда
            int l = Integer.parseInt(st.nextToken()); // l или l
            int r = Integer.parseInt(st.nextToken()); // r или r

            if (command == 1) {
                int v = Integer.parseInt(st.nextToken()); // v
                plus(0, 0, n - 1, l, r - 1, v);
            } else {
                System.out.println(RMQ(0, 0, n - 1, l, r - 1));
            }
        }
    }

    public static void plus(int v, int tl, int tr, int l, int r, int value) {
        if (l > r) return;

        if (tl == l && tr == r) {
            tree[v] += value;
            promises[v] += value;
            return;
        }

        push(v, tl, tr);

        int tm = (tl + tr) / 2;

        plus(v * 2 + 1, tl, tm, l, Math.min(r, tm), value);
        plus(v * 2 + 2, tm + 1, tr, Math.max(l, tm + 1), r, value);

        tree[v] = Math.min(tree[2 * v + 1], tree[2 * v + 2]);

    }

    public static long RMQ(int v, int tl, int tr, int l, int r) {
        if (l > r) return Long.MAX_VALUE;

        if (tl == l && tr == r) {
            return tree[v];
        }

        push(v, tl, tr);

        int tm = (tl + tr) / 2;

        return Math.min(RMQ(v * 2 + 1, tl, tm, l, Math.min(r, tm)),
                        RMQ(v * 2 + 2, tm + 1, tr, Math.max(l, tm + 1), r));

    }

    public static void push(int v, int tl, int tr) {
        if (promises[v] != 0) {
            if (tl != tr) { // Если не лист — передаем детям
                long p = promises[v];
                int left = v * 2 + 1;
                int right = v * 2 + 2;

                promises[left] += p;
                promises[right] += p;

                // ОБЯЗАТЕЛЬНО обновляем tree у детей сразу
                tree[left] += p;
                tree[right] += p;
            }
            promises[v] = 0;
        }
    }
}
