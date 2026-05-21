package com.github.pathfind1103.problems.tbank.contests.contest10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Transport {
    static class Edge {
        int to, time, capacity;
        Edge(int to, int time, int capacity) {
            this.to = to;
            this.time = time;
            this.capacity = capacity;
        }
    }

    static class Node implements Comparable<Node> {
        int id, time;
        Node(int id, int time) {
            this.id = id;
            this.time = time;
        }
        public int compareTo(Node other) {
            return Integer.compare(this.time, other.time);
        }
    }

    static List<Edge>[] adj;
    static int N, M;
    static final int MAX_TIME = 1440;
    static final int TRUCK_WEIGHT = 3000000;
    static final int CUP_WEIGHT = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adj[u].add(new Edge(v, t, c));
            adj[v].add(new Edge(u, t, c));
        }

        // Бинарный поиск по количеству кружек
        int low = 0, high = 10000000;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canDeliver(mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println(ans);
    }

    static boolean canDeliver(int mugs) {
        long requiredWeight = (long)TRUCK_WEIGHT + (long)mugs * CUP_WEIGHT;
        int[] minTime = new int[N + 1];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        minTime[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.time > minTime[curr.id]) continue;
            if (curr.id == N) break;

            for (Edge e : adj[curr.id]) {
                if (e.capacity >= requiredWeight) {
                    if (minTime[curr.id] + e.time < minTime[e.to]) {
                        minTime[e.to] = minTime[curr.id] + e.time;
                        pq.add(new Node(e.to, minTime[e.to]));
                    }
                }
            }
        }
        return minTime[N] <= MAX_TIME;
    }
}
