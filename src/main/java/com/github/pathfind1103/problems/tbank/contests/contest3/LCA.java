package com.github.pathfind1103.problems.tbank.contests.contest3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCA {
    static int[][] up;
    static int[] depth;
    static int LOG = 18;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;

        int n = Integer.parseInt(line.trim());

        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        if (n > 1) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n; i++) {
                int parent = Integer.parseInt(st.nextToken());
                adj[parent].add(i);
            }
        }

        // Инициализируем массивы
        up = new int[n][LOG];
        depth = new int[n];

        // Запускаем DFS
        dfs(0, 0, 0, adj);

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            System.out.println(lca(u, v));
        }

    }

    static void dfs(int current, int parent, int currentDepth, ArrayList<Integer>[] adj) {
        // Запоминаем глубину текущей вершины
        depth[current] = currentDepth;

        // Самый первый прыжок (2^0 = 1) — это всегда переход к родителю
        up[current][0] = parent;

        // Заполняем таблицу прыжков для текущей вершины
        for (int power = 1; power < LOG; power++) {
            // Чтобы прыгнуть на 2^power, нужно:
            // 1. Прыгнуть на 2^(power-1) из текущей вершины -> попадаем в "middleNode"
            // 2. Прыгнуть еще на 2^(power-1) уже из "middleNode"
            int middleNode = up[current][power - 1];
            up[current][power] = up[middleNode][power - 1];
        }

        // Рекурсивно идем во всех детей текущей вершины
        for (int child : adj[current]) {
            // Для ребенка текущая вершина становится родителем, а глубина растет на 1
            dfs(child, current, currentDepth + 1, adj);
        }
    }

    static int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int i = 0; i < LOG; i++) {
            if (((diff >> i) & 1) == 1) {
                u = up[u][i];
            }
        }

        if (u == v) return u;

        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }

        return up[u][0];
    }
}
