package com.github.pathfind1103.problems.tbank.contests.contest9;

import java.io.*;
import java.util.*;

public class Windows {
    static final int OFFSET = 200000;
    static final int MAX_Y = 400001;

    static int[] tree;     // Максимальное покрытие в узле
    static int[] treePos;  // Координата Y, где достигнут максимум
    static int[] lazy;     // Ленивое обновление (+1 / -1)

    static class Event implements Comparable<Event> {
        int x, y1, y2, type;
        Event(int x, int y1, int y2, int type) {
            this.x = x; this.y1 = y1; this.y2 = y2; this.type = type;
        }
        @Override
        public int compareTo(Event o) {
            if (this.x != o.x) return Integer.compare(this.x, o.x);
            // Открытие (type=1) должно быть раньше закрытия (type=-1)
            return Integer.compare(o.type, this.type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        if (n == 0) return;

        Event[] events = new Event[2 * n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken()) + OFFSET;
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken()) + OFFSET;
            events[2 * i] = new Event(x1, y1, y2, 1);
            events[2 * i + 1] = new Event(x2, y1, y2, -1);
        }
        Arrays.sort(events);

        tree = new int[4 * MAX_Y];
        treePos = new int[4 * MAX_Y];
        lazy = new int[4 * MAX_Y];
        build(0, 0, MAX_Y - 1);

        int maxWindows = -1;
        int resX = 0, resY = 0;

        for (Event e : events) {
            update(0, 0, MAX_Y - 1, e.y1, e.y2, e.type);
            if (tree[0] > maxWindows) {
                maxWindows = tree[0];
                resX = e.x;
                resY = treePos[0] - OFFSET;
            }
        }

        System.out.println(maxWindows);
        System.out.println(resX + " " + resY);
    }

    static void build(int v, int tl, int tr) {
        if (tl == tr) {
            treePos[v] = tl;
        } else {
            int tm = (tl + tr) / 2;
            build(2 * v + 1, tl, tm);
            build(2 * v + 2, tm + 1, tr);
            treePos[v] = treePos[2 * v + 1];
        }
    }

    static void push(int v) {
        if (lazy[v] != 0) {
            int left = 2 * v + 1;
            int right = 2 * v + 2;

            lazy[left] += lazy[v];
            tree[left] += lazy[v];

            lazy[right] += lazy[v];
            tree[right] += lazy[v];

            lazy[v] = 0;
        }
    }

    static void update(int v, int tl, int tr, int l, int r, int add) {
        if (l > r) return;
        if (l == tl && r == tr) {
            tree[v] += add;
            lazy[v] += add;
        } else {
            push(v);
            int tm = (tl + tr) / 2;
            update(2 * v + 1, tl, tm, l, Math.min(r, tm), add);
            update(2 * v + 2, tm + 1, tr, Math.max(l, tm + 1), r, add);

            if (tree[2 * v + 1] >= tree[2 * v + 2]) {
                tree[v] = tree[2 * v + 1];
                treePos[v] = treePos[2 * v + 1];
            } else {
                tree[v] = tree[2 * v + 2];
                treePos[v] = treePos[2 * v + 2];
            }
        }
    }
}
