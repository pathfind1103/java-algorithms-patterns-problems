package com.github.pathfind1103.problems.yandex.sbd2026.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TaskC {
    static class Point implements Comparable<Point> {
        long x, y;
        Point(long x, long y) {
            this.x = Math.abs(x);
            this.y = Math.abs(y);
        }

        public int compareTo(Point other) {
            if (this.x != other.x) {
                return Long.compare(this.x, other.x);
            }

            return  Long.compare(this.y, other.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line  = br.readLine();
        if (line == null) return;

        int n = Integer.parseInt(line.trim());
        Point[] allPoints = new Point[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            allPoints[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }

        Arrays.sort(allPoints);

        ArrayList<Point> points = new ArrayList<>();
        for (Point p : allPoints) {
            while (!points.isEmpty() && points.get(points.size() - 1).y <= p.y) {
                points.remove(points.size() - 1);
            }

            points.add(p);
        }

        int m = points.size();
        long[] dp = new long[m + 1];

        for (int i = 1; i <= m; i++) {
            dp[i] = Long.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                long currentArea = 4 * points.get(i - 1).x * points.get(j - 1).y;
                dp[i] = Math.min(dp[i], dp[j - 1] + currentArea);
            }
        }

        System.out.println(dp[m]);
    }
}
