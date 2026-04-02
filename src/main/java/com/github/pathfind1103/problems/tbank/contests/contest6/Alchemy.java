package com.github.pathfind1103.problems.tbank.contests.contest6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Alchemy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.isEmpty()) return;
        int m = Integer.parseInt(line.trim());

        int id = 1;
        HashMap<String, Integer> elements = new HashMap<>();
        ArrayList<Integer>[] adj = new ArrayList[201];
        for (int i = 0; i < 201; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String row = br.readLine();
            if (row == null) break;

            String[] parts = row.split("->");
            String element1 = parts[0].trim();
            String element2 = parts[1].trim();

            if (!elements.containsKey(element1)) elements.put(element1, id++);
            if (!elements.containsKey(element2)) elements.put(element2, id++);

            adj[elements.get(element1)].add(elements.get(element2));
        }

        String start = br.readLine();
        String end = br.readLine();

        if (start == null || end == null) return;
        start = start.trim();
        end = end.trim();

        if (start.equals(end)) {
            System.out.println(0);
            return;
        }

        if (!elements.containsKey(start) || !elements.containsKey(end)) {
            System.out.println(-1);
            return;
        }

        int startPoint = elements.get(start);
        int endPoint = elements.get(end);

        int[] dist = new int[id + 1];
        Arrays.fill(dist, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        queue.add(startPoint);
        dist[startPoint] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == endPoint) {
                System.out.println(dist[node]);
                return;
            }

            for (int neigh : adj[node]) {
                if (dist[neigh] == -1) {
                    dist[neigh] = dist[node] + 1;
                    queue.add(neigh);
                }
            }
        }

        System.out.println(-1);
    }
}
