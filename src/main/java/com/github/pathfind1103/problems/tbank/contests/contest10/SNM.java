package com.github.pathfind1103.problems.tbank.contests.contest10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SNM {
    static private int[] parent;
    static private int[] min;
    static private int[] max;
    static private int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // Кол-во элементов в наборе
        int m = Integer.parseInt(st.nextToken()); // Кол-во операций

        init(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("union")) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                union(x, y);
            } else {
                int x = Integer.parseInt(st.nextToken());
                get(x);
            }
        }

    }

    public static void init(int n) {
        parent = new int[n + 1];
        min = new int[n + 1];
        max = new int[n + 1];
        size = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            min[i] = i;
            max[i] = i;
            size[i] = 1;
        }
    }

    public static void union(int x, int y) {
        int xRoot = findRoot(x);
        int yRoot = findRoot(y);

        if (xRoot != yRoot) {
            parent[xRoot] = yRoot;
            min[yRoot] = Math.min(min[xRoot], min[yRoot]);
            max[yRoot] = Math.max(max[xRoot], max[yRoot]);
            size[yRoot] += size[xRoot];
        }
    }

    public static void get(int x) {
        int xRoot = findRoot(x);
        System.out.println(min[xRoot] + " " + max[xRoot] + " " + size[xRoot]);
    }

    public static int findRoot(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = findRoot(parent[x]);
        }
    }
}
