package com.github.pathfind1103.problems.tbank.contests.contest10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TheBackboneTree {
    static private int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        init(n);

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int b = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.add(new Edge(b, e, w));
        }

        edges.sort((a, b) -> Integer.compare(a.weight, b.weight));

        long result = 0;

        for (int i = 0; i < edges.size(); i++) {
            Edge currentEdge = edges.get(i);
            int bRoot = findRoot(currentEdge.u);
            int eRoot = findRoot(currentEdge.v);

            if (bRoot != eRoot) {
                union(eRoot, bRoot);
                result += currentEdge.weight;
            }
        }

        System.out.println(result);
    }

    static class Edge {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static void init(int n) {
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    public static void union(int xRoot, int yRoot) {
        if (xRoot != yRoot) {
            parent[xRoot] = yRoot;
        }
    }

    public static int findRoot(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = findRoot(parent[x]);
        }
    }
}
