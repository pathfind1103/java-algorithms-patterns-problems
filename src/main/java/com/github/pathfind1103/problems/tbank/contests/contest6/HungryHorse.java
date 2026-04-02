package com.github.pathfind1103.problems.tbank.contests.contest6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HungryHorse {
    static int[][] dist;
    static Point[][] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

        dist = new int[n+1][n+1];
        for (int[] row : dist) Arrays.fill(row, -1);
        parent = new Point[n+1][n+1];

        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x1, y1));
        dist[x1][y1] = 0;

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            if (curr.x == x2 && curr.y == y2) break;

            for (int i = 0; i < 8; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx >= 1 && nx <= n && ny >= 1 && ny <= n && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[curr.x][curr.y] + 1;
                    parent[nx][ny] = curr;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        System.out.println(dist[x2][y2]);

        ArrayList<Point> path = new ArrayList<>();
        Point curr = new Point(x2, y2);
        while (curr != null) {
            path.add(curr);
            curr = parent[curr.x][curr.y];
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            Point p = path.get(i);
            System.out.println(p.x + " " + p.y);
        }

    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
