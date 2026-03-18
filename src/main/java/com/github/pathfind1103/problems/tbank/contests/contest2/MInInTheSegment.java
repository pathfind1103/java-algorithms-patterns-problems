package com.github.pathfind1103.problems.tbank.contests.contest2;

import java.io.*;
import java.util.*;

public class MInInTheSegment {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] window = new int[n];
        ArrayDeque<Integer> dq = new ArrayDeque<>(k);

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.getFirst() <= i - k) {
                dq.removeFirst();
            }

            int value = Integer.parseInt(st.nextToken());
            window[i] = value;

            while (!dq.isEmpty() && window[dq.getLast()] >= window[i]) {
                dq.removeLast();
            }

            dq.add(i);

            if (i >= k - 1) {
                stringBuilder.append(window[dq.getFirst()]).append(' ');
            }
        }

        out.print(stringBuilder.toString().trim());
        out.flush();
        out.close();
    }
}