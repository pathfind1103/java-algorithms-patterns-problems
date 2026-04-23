package com.github.pathfind1103.problems.tbank.contests.contest8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TheFirstElemenetIsAtLeastX {
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
            int a = Integer.parseInt(st.nextToken()); // i или x
            int b = Integer.parseInt(st.nextToken()); // v или l

            if (command == 1) {
                update(0, 0, n - 1, a, b);
            } else {
                System.out.println(getMinIndex(0, 0, n - 1, a, b));
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
        tree[start] = Math.max(tree[2 * start + 1], tree[2 * start + 2]);
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
            tree[start] = Math.max(tree[2 * start + 1], tree[2 * start + 2]);
        }
    }

    public static int getMinIndex(int start, int tl, int tr, int x, int l) {
        if (tree[start] < x || tr < l) return -1;

        if (tr == tl) {
            return tl;
        }

        int tm = (tl + tr) / 2;

        // 3. Сначала пробуем найти в ЛЕВОМ ребенке
        int res = getMinIndex(2 * start + 1, tl, tm, x, l);

        // 4. Если левый ребенок не дал результата, идем в ПРАВОГО
        if (res == -1) {
            res = getMinIndex(2 * start + 2, tm + 1, tr, x, l);
        }

        return res;

    }
}
