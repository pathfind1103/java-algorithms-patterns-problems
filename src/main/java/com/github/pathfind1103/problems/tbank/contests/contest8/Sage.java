package com.github.pathfind1103.problems.tbank.contests.contest8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sage {
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // Кол-во моментов времени
        int m = Integer.parseInt(st.nextToken()); // Число запросов

        tree = new long[4 * n]; //Дерево отрезков
        long[] arrayOfA_i = new long[n]; // Список кол-ва логов в каждый момент времени

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arrayOfA_i[i] = Integer.parseInt(st.nextToken());
        }

        // Заполняем дерево отрезков
        initial(0, 0, n -1, arrayOfA_i);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken()); // Команда
            int a = Integer.parseInt(st.nextToken()); // i или l
            int b = Integer.parseInt(st.nextToken()); // v или r

            if (command == 1) {
                update(0, 0, n - 1, a, b);
            } else {
                System.out.println(getSum(0, 0, n - 1, a, b - 1));
            }
        }


    }

    public static void initial(int start, int l, int r, long[] array) {
        // Если в листе, то записываем значение из массива (отрезок длинны 1)
        if (l == r) {
            tree[start] = array[l];
            return;
        }

        int m = (l + r) / 2;

        // Проваливаемся в детей
        initial(start * 2 + 1, l, m, array);
        initial(start * 2 + 2, m + 1, r, array);

        // Если мы все же не в листе, то просто устанавливаем сумму вершины равной значениям детей
        tree[start] = tree[2 * start + 1] + tree[2 * start + 2];
    }

    public static void update(int start, int l, int r, int pos, int newValue) {
        if (l == r) {
            tree[start] = newValue; // Дошли до нужного листа
        } else {
            int m = (l + r) / 2;

            if (pos <= m)
                update(2 * start + 1, l, m, pos, newValue);
            else
                update(2 * start + 2, m + 1, r, pos, newValue);

            // Пересчитываем только текущего родителя
            tree[start] = tree[2 * start + 1] + tree[2 * start + 2];
        }
    }

    public static long getSum(int v, int tl, int tr, int l, int r) {
        if (l > r) return 0;
        if (l == tl && r == tr) return tree[v];

        int tm = (tl + tr) / 2;
        return getSum(2 * v + 1, tl, tm, l, Math.min(r, tm))
                + getSum(2 * v + 2, tm + 1, tr, Math.max(l, tm + 1), r);
    }
}
