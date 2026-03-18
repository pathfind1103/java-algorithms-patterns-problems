package com.github.pathfind1103.problems.tbank.contests.contest4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class PaintingStraightLine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        TreeMap<Long, Integer> tree = new TreeMap<>();
        int n = Integer.parseInt(br.readLine());
        Long start = (long) -1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long l = Long.parseLong(st.nextToken());
            long r = Long.parseLong(st.nextToken());

            if (start == -1) {
                start = l;
            }

            tree.put(l, tree.getOrDefault(l, 0) + 1);
            tree.put(r, tree.getOrDefault(r, 0) - 1);
        }

        long totalLength = 0;
        int activeSegments = 0;
        Long lastCoord = null;

        for (var entry : tree.entrySet()) {
            long currentCoord = entry.getKey();
            int delta = entry.getValue();

            if (activeSegments > 0) {
                totalLength += (currentCoord - lastCoord);
            }

            activeSegments += delta;
            lastCoord = currentCoord;
        }

        System.out.println(totalLength);
    }
}
