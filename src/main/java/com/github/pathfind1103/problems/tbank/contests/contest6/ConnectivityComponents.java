package com.github.pathfind1103.problems.tbank.contests.contest6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ConnectivityComponents {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static ArrayList<Integer> currentComponent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        List<List<Integer>> components = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                currentComponent = new ArrayList<>();
                dfs(i);
                Collections.sort(currentComponent);
                components.add(currentComponent);
            }
        }

        System.out.println(components.size());
        for (List<Integer> comp : components) {
            System.out.println(comp.size());
            for (int node : comp) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

    public static void dfs(int v) {
        visited[v] = true;
        currentComponent.add(v);
        for (int neigh : adj[v]) {
            if (!visited[neigh]) {
                dfs(neigh);
            }
        }
    }
}
