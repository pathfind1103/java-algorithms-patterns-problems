package com.github.pathfind1103.problems.tbank.contests.contest9;

import java.io.*;
import java.util.*;

public class Artist {
    static final int OFFSET = 500000;
    static final int MAX_COORD = 1000001;

    static int[] treeLen;   // Суммарная длина черного
    static int[] treeCnt;   // Количество черных отрезков
    static int[] leftB;     // 1 если левый край узла черный
    static int[] rightB;    // 1 если правый край узла черный
    static int[] set;       // -1: нет, 0: белый, 1: черный

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        treeLen = new int[4 * MAX_COORD];
        treeCnt = new int[4 * MAX_COORD];
        leftB = new int[4 * MAX_COORD];
        rightB = new int[4 * MAX_COORD];
        set = new int[4 * MAX_COORD];
        Arrays.fill(set, -1);

        PrintWriter out = new PrintWriter(System.out);

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String color = st.nextToken();
            int x = Integer.parseInt(st.nextToken()) + OFFSET;
            int len = Integer.parseInt(st.nextToken());

            // Отрезок [x, x + len - 1]
            update(0, 0, MAX_COORD - 1, x, x + len - 1, color.equals("B") ? 1 : 0);

            out.println(treeCnt[0] + " " + treeLen[0]);
        }
        out.close();
    }

    static void apply(int v, int tl, int tr, int value) {
        if (value == 1) { // Черный
            treeLen[v] = (tr - tl + 1);
            treeCnt[v] = 1;
            leftB[v] = rightB[v] = 1;
        } else { // Белый
            treeLen[v] = 0;
            treeCnt[v] = 0;
            leftB[v] = rightB[v] = 0;
        }
        set[v] = value;
    }

    static void push(int v, int tl, int tr) {
        if (set[v] != -1) {
            int tm = (tl + tr) / 2;
            apply(2 * v + 1, tl, tm, set[v]);
            apply(2 * v + 2, tm + 1, tr, set[v]);
            set[v] = -1;
        }
    }

    static void combine(int v) {
        int left = 2 * v + 1;
        int right = 2 * v + 2;
        treeLen[v] = treeLen[left] + treeLen[right];
        leftB[v] = leftB[left];
        rightB[v] = rightB[right];
        treeCnt[v] = treeCnt[left] + treeCnt[right];

        // Если на стыке оба края черные — два отрезка превращаются в один
        if (rightB[left] == 1 && leftB[right] == 1) {
            treeCnt[v]--;
        }
    }

    static void update(int v, int tl, int tr, int l, int r, int value) {
        if (l > r) return;
        if (l == tl && r == tr) {
            apply(v, tl, tr, value);
            return;
        }
        push(v, tl, tr);
        int tm = (tl + tr) / 2;
        update(2 * v + 1, tl, tm, l, Math.min(r, tm), value);
        update(2 * v + 2, tm + 1, tr, Math.max(l, tm + 1), r, value);
        combine(v);
    }
}
