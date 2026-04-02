package com.github.pathfind1103.problems.tbank.contests.contest6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IsThereACycle {
    static ArrayList<Integer>[] adj;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        visited = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
        }


        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                if (dfs(i) == 1) {
                    System.out.println(1);
                    return;
                }
            }
        }

        System.out.println(0);
    }

    public static int dfs(int v) {
        visited[v] = 1;

        for (int neigh : adj[v]) {
            if (visited[neigh] == 1) {
                return 1;
            }

            if (visited[neigh] == 0) {
                if (dfs(neigh) == 1) return 1;
            }
        }

        visited[v] = 2;
        return 0;
    }
}
