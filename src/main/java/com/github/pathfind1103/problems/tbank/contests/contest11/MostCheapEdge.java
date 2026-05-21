package com.github.pathfind1103.problems.tbank.contests.contest11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MostCheapEdge {
    static ArrayList<Edge>[] adj;
    static int[] tin;
    static int[] tout;
    static int[][] up;
    static int[][] minEdge;
    static int timer = 0;
    static final int LOG = 18;

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        up = new int[n][LOG];
        minEdge = new int[n][LOG];
        tin = new int[n];
        tout = new int[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st;

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj[parent].add(new Edge(i, weight));
        }

        up[0][0] = 0;
        minEdge[0][0] = 0;

        dfs(0, 0, Integer.MAX_VALUE);

        String mLine = br.readLine();
        if (mLine != null) {
            int m = Integer.parseInt(mLine.trim());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                int lca = findLCA(u, v);
                int result = Math.min(getMinUp(u, lca), getMinUp(v, lca));
                out.println(result);
            }
        }

        out.flush();
        out.close();
    }

    public static void dfs(int v, int p, int edgeWeight) {
        tin[v] = ++timer;

        up[v][0] = p;
        minEdge[v][0] = edgeWeight;

        for (int i = 1; i < LOG; i++) {
            up[v][i] = up[up[v][i - 1]][i - 1];
            minEdge[v][i] = Math.min(minEdge[v][i - 1], minEdge[up[v][i - 1]][i - 1]);
        }

        for (Edge e : adj[v]) {
            dfs(e.to, v, e.weight);
        }

        tout[v] = ++timer;
    }

    public static boolean isAncestors(int u, int v) {
        return tin[u] <= tin[v] && tout[v] <= tout[u];
    }

    public static int findLCA(int u, int v) {
        int res = Integer.MAX_VALUE;

        if (isAncestors(u, v)) return u;
        if (isAncestors(v, u)) return v;

        for (int i = LOG - 1; i >= 0; i--) {
            if (!isAncestors(up[u][i], v)) {
                res = Math.min(res, minEdge[u][i]);
                u = up[u][i];
            }
        }

        return up[u][0];
    }

    public static int getMinUp(int v, int target) {
        if (v == target) return Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for (int i = LOG - 1; i >= 0; i--) {
            // Если прыжок на 2^i не перепрыгивает выше target
            if (!isAncestors(up[v][i], target)) {
                res = Math.min(res, minEdge[v][i]);
                v = up[v][i];
            }
        }
        // Добавляем последнее ребро до самого target
        return Math.min(res, minEdge[v][0]);
    }
}
