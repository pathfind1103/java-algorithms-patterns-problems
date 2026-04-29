package com.github.pathfind1103.problems.tbank.contests.inter25042026;

import java.io.*;
import java.util.*;

public class Museum {
    static Map<Long, Long>[] memory;
    static long[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        a = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        memory = new HashMap[n];
        for (int i = 0; i < n; i++) {
            memory[i] = new HashMap<>();
        }

        System.out.println(solve(n - 1, x));
    }

    static long solve(int i, long rem) {
        if (i == 0) {
            return rem;
        }

        if (rem == 0) {
            return 0;
        }

        if (memory[i].containsKey(rem)) {
            return memory[i].get(rem);
        }

        long res = (rem / a[i]) + solve(i - 1, rem % a[i]);

        if (rem % a[i] != 0) {
            long resUp = (rem / a[i] + 1) + solve(i - 1, a[i] - (rem % a[i]));
            res = Math.min(res, resUp);
        }

        memory[i].put(rem, res);
        return res;
    }
}
