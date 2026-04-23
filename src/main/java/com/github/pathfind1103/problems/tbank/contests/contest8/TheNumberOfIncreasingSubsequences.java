package com.github.pathfind1103.problems.tbank.contests.contest8;

import java.io.*;
import java.util.*;

public class TheNumberOfIncreasingSubsequences {
    static final int MOD = 1_000_000_007;

    // Структура для хранения результата в узлах дерева
    static class Node {
        int maxL;
        int cnt;

        Node(int maxL, int cnt) {
            this.maxL = maxL;
            this.cnt = cnt;
        }
    }

    static Node[] tree;

    // Функция объединения двух узлов (сердце алгоритма)
    static Node combine(Node left, Node right) {
        if (left.maxL > right.maxL) return new Node(left.maxL, left.maxL == 0 ? 0 : left.cnt);
        if (right.maxL > left.maxL) return new Node(right.maxL, right.maxL == 0 ? 0 : right.cnt);
        if (left.maxL == 0) return new Node(0, 0);
        // Если длины равны, суммируем количества по модулю
        return new Node(left.maxL, (left.cnt + right.cnt) % MOD);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 0) { System.out.println(0); return; }

        int[] a = new int[n];
        int[] sortedA = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            sortedA[i] = a[i];
        }

        // 1. Сжатие координат
        Arrays.sort(sortedA);
        int m = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (i == 0 || sortedA[i] != sortedA[i - 1]) {
                map.put(sortedA[i], m++);
            }
        }

        // 2. Инициализация дерева отрезков
        tree = new Node[4 * m];
        for (int i = 0; i < 4 * m; i++) tree[i] = new Node(0, 0);

        // 3. Проход по массиву
        for (int i = 0; i < n; i++) {
            int val = map.get(a[i]);

            // Ищем лучший результат среди чисел меньше текущего
            Node best = query(0, 0, m - 1, 0, val - 1);

            int curL, curCnt;
            if (best.maxL == 0) {
                curL = 1;
                curCnt = 1;
            } else {
                curL = best.maxL + 1;
                curCnt = best.cnt;
            }

            // Обновляем дерево в позиции текущего числа
            update(0, 0, m - 1, val, new Node(curL, curCnt));
        }

        // Ответ в корне дерева (макс. длина и сумма количеств)
        System.out.println(tree[0].cnt);
    }

    static void update(int v, int tl, int tr, int pos, Node newNode) {
        if (tl == tr) {
            // Если в этой позиции уже была такая же длина, прибавляем количество
            if (newNode.maxL == tree[v].maxL) {
                tree[v] = new Node(tree[v].maxL, (tree[v].cnt + newNode.cnt) % MOD);
            } else if (newNode.maxL > tree[v].maxL) {
                tree[v] = newNode;
            }
        } else {
            int tm = (tl + tr) / 2;
            if (pos <= tm) update(2 * v + 1, tl, tm, pos, newNode);
            else update(2 * v + 2, tm + 1, tr, pos, newNode);
            tree[v] = combine(tree[2 * v + 1], tree[2 * v + 2]);
        }
    }

    static Node query(int v, int tl, int tr, int l, int r) {
        if (l > r) return new Node(0, 0);
        if (l == tl && r == tr) return tree[v];
        int tm = (tl + tr) / 2;
        return combine(query(2 * v + 1, tl, tm, l, Math.min(r, tm)),
                query(2 * v + 2, tm + 1, tr, Math.max(l, tm + 1), r));
    }
}
