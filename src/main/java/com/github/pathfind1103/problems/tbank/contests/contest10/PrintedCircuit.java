package com.github.pathfind1103.problems.tbank.contests.contest10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrintedCircuit {
    static private int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        init(n * m);

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= m; j++) {
                int op = Integer.parseInt(st.nextToken());

                if (op == 0) {
                    continue;
                } else if (op == 1) {
                    union((i - 1) * m + j, (i - 1 + 1) * m + j);
                } else if (op == 2) {
                    union((i - 1) * m + j, (i - 1) * m + (j + 1));
                } else {
                    union((i - 1) * m + j, (i - 1 + 1) * m + j);
                    union((i - 1) * m + j, (i - 1) * m + (j + 1));
                }
            }
        }

        ArrayList<Edge> possibleEdges = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // Потенциальная вертикальная (вес 1)
                if (i < n) {
                    possibleEdges.add(new Edge(i, j, 1)); // координаты и тип
                }
                // Потенциальная горизонтальная (вес 2)
                if (j < m) {
                    possibleEdges.add(new Edge(i, j, 2));
                }
            }
        }

        possibleEdges.sort((a, b) -> Integer.compare(a.weight, b.weight));

        int totalCost = 0;
        ArrayList<Edge> addedEdges = new ArrayList<>();

        for (Edge edge : possibleEdges) {
            // Индекс текущего узла (r, c)
            int u = (edge.r - 1) * m + edge.c;
            int v;

            // Находим индекс соседа в зависимости от типа перемычки
            if (edge.type == 1) {
                // Сосед снизу: строка увеличивается (r + 1), столбец тот же
                v = edge.r * m + edge.c;
            } else {
                // Сосед справа: строка та же, столбец увеличивается (c + 1)
                v = (edge.r - 1) * m + (edge.c + 1);
            }

            int rootU = findRoot(u);
            int rootV = findRoot(v);

            if (rootU != rootV) {
                union(rootU, rootV);
                totalCost += edge.weight;
                addedEdges.add(edge);
            }
        }


        System.out.println(addedEdges.size() + " " + totalCost);
        for (Edge edge : addedEdges) {
            System.out.println(edge.r + " " + edge.c + " " + edge.type);
        }
    }

    static class Edge {
        int r, c, type, weight;

        Edge(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
            this.weight = (type == 1) ? 1 : 2; // Вертикаль = 1, Горизонталь = 2
        }
    }


    public static void init(int n) {
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    public static void union(int x, int y) {
        int xRoot = findRoot(x);
        int yRoot = findRoot(y);
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
