package com.github.pathfind1103.problems.tbank.contests.contest3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Primitives {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int[] tree = new int[n];
        int[] memo = new int[n];
        tree[0] = -1;

        // Создаем массив вершин
        for (int i = 1; i < n; i++) {
            tree[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        //1. Глубина
        int depth = 0;
        for (int i = 1; i < n; i++) {
            int nextNodeDepth = getDepth(i, tree, memo);

            if (nextNodeDepth > depth) {
                depth = nextNodeDepth;
            }
        }

        System.out.print(depth - 1 + " ");

        // Создаем массив смежности
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int p = tree[i];

            if (p != -1) {
                adj[i].add(p);
                adj[p].add(i);
            }
        }

        //2. Вычисление диаметра с помощью BFS
        int[] firstRun = BFS(0, n, adj);
        int[] secondRun = BFS(firstRun[0], n, adj);

        System.out.println(secondRun[1]);

        // 3. Вычисление глубины для КАЖДОЙ вершины
        int[] allDepths = getAllDepths(0, n, adj);

        // Вывод глубин (обычно через пробел)
        StringBuilder sb = new StringBuilder();
        for (int d : allDepths) {
            sb.append(d).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    public static int getDepth(int node, int[] tree, int[] memo) {
        if (memo[node] != 0) {
            return memo[node];
        }

        int count = 1;
        int current = node;

        while (tree[current] != -1) {
            current = tree[current];
            if (memo[current] != 0) {
                count += memo[current];
                break;
            }

            count++;
        }

        memo[node] = count;
        return count;
    }

    public static int[] BFS(int start, int n, ArrayList<Integer>[] adj) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        dist[start] = 0;
        queue.add(start);

        int farthestNode = start;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (Integer neighbour : adj[v]) {
                if (dist[neighbour] == -1) {
                    dist[neighbour] = dist[v] + 1;
                    queue.add(neighbour);

                    if (dist[neighbour] > dist[farthestNode]) {
                        farthestNode = neighbour;
                    }
                }
            }
        }

        return new int[]{farthestNode, dist[farthestNode]};
    }

    public static int[] getAllDepths(int start, int n, ArrayList<Integer>[] adj) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        dist[start] = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (Integer neighbour : adj[v]) {
                if (dist[neighbour] == -1) {
                    dist[neighbour] = dist[v] + 1;
                    queue.add(neighbour);
                }
            }
        }
        return dist;
    }

}

