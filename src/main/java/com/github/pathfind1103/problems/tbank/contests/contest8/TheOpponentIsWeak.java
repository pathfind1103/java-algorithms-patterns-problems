package com.github.pathfind1103.problems.tbank.contests.contest8;

import java.io.*;
import java.util.*;

public class TheOpponentIsWeak {
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] sortedA = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            sortedA[i] = a[i];
        }

        // 1. Сжатие координат
        Arrays.sort(sortedA);
        Map<Integer, Integer> map = new HashMap<>();
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sortedA[i] != sortedA[i - 1]) {
                map.put(sortedA[i], m++);
            }
        }

        int[] ranks = new int[n];
        for (int i = 0; i < n; i++) ranks[i] = map.get(a[i]);

        // 2. Считаем, сколько чисел БОЛЬШЕ текущего стоит СЛЕВА
        int[] greaterLeft = new int[n];
        tree = new long[4 * m];
        for (int i = 0; i < n; i++) {
            // Запрос: сколько чисел в диапазоне [ranks[i] + 1, m - 1] уже было
            greaterLeft[i] = (int) query(0, 0, m - 1, ranks[i] + 1, m - 1);
            update(0, 0, m - 1, ranks[i], 1);
        }

        // 3. Считаем, сколько чисел МЕНЬШЕ текущего стоит СПРАВА
        int[] smallerRight = new int[n];
        Arrays.fill(tree, 0); // Очищаем дерево
        for (int i = n - 1; i >= 0; i--) {
            // Запрос: сколько чисел в диапазоне [0, ranks[i] - 1] уже было
            smallerRight[i] = (int) query(0, 0, m - 1, 0, ranks[i] - 1);
            update(0, 0, m - 1, ranks[i], 1);
        }

        // 4. Считаем итоговое количество троек
        long totalWeakness = 0;
        for (int i = 0; i < n; i++) {
            totalWeakness += (long) greaterLeft[i] * smallerRight[i];
        }

        System.out.println(totalWeakness);
    }

    // Обычное дерево отрезков на сумму
    static void update(int v, int tl, int tr, int pos, int add) {
        if (tl == tr) {
            tree[v] += add;
        } else {
            int tm = (tl + tr) / 2;
            if (pos <= tm) update(2 * v + 1, tl, tm, pos, add);
            else update(2 * v + 2, tm + 1, tr, pos, add);
            tree[v] = tree[2 * v + 1] + tree[2 * v + 2];
        }
    }

    static long query(int v, int tl, int tr, int l, int r) {
        if (l > r) return 0;
        if (l == tl && r == tr) return tree[v];
        int tm = (tl + tr) / 2;
        return query(2 * v + 1, tl, tm, l, Math.min(r, tm))
                + query(2 * v + 2, tm + 1, tr, Math.max(l, tm + 1), r);
    }
}
