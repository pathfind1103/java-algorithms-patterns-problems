package com.github.pathfind1103.problems.tbank.contests.contest6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TopologicSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1 = br.readLine();
        if (line1 == null) return;
        StringTokenizer st = new StringTokenizer(line1);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            from[i] = Integer.parseInt(st.nextToken());
            to[i] = Integer.parseInt(st.nextToken());
        }

        int[] pos = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            pos[v] = i;
        }

        boolean ok = true;
        for (int i = 0; i < m; i++) {
            if (pos[from[i]] > pos[to[i]]) {
                ok = false;
                break;
            }
        }

        System.out.println(ok ? "YES" : "NO");
    }

}
