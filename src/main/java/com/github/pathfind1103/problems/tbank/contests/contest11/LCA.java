package com.github.pathfind1103.problems.tbank.contests.contest11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCA {
    static ArrayList<Integer>[] adj;
    static int[] tin;
    static int[] tout;
    static int[][] up;
    static int timer = 0;
    static final int LOG = 18;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        up = new int[n][18];
        tin = new int[n];
        tout = new int[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            adj[parent].add(i);
            up[i][0] = parent;
        }

        up[0][0] = 0;

        dfs(0, 0);

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            out.println(getLCA(u, v));
        }

        out.flush();
        out.close();
    }

    public static void dfs(int v, int p) {
        tin[v] = ++timer;

        up[v][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[v][i] = up[up[v][i - 1]][i - 1];
        }

        for (int to : adj[v]) {
            dfs(to, v);
        }

        tout[v] = ++timer;
    }

    public static boolean isAncestors(int u, int v) {
        return tin[u] <= tin[v] && tout[v] <= tout[u];
    }

    public static int getLCA(int u, int v) {
        if (isAncestors(u, v)) return u;
        if (isAncestors(v, u)) return v;

        for (int i = LOG - 1; i >= 0; i--) {
            if (!isAncestors(up[u][i], v)) {
                u = up[u][i];
            }
        }

        return up[u][0];
    }
}
