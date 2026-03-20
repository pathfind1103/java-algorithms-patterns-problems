package com.github.pathfind1103.problems.tbank.contests.contest5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SubstringСomparisons {
    static long[] h;
    static long[] p;
    static long mod = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String input = br.readLine();
        int sLen = input.length();
        char[] string = input.toCharArray();

        int n = Integer.parseInt(br.readLine());
        h = new long[sLen + 1];
        p = new long[sLen + 1];
        p[0] = 1;
        h[0] = 0;

        Arrays.fill(h, -1);

        int k = 31;

        for (int i = 1; i <= sLen; i++) {
            p[i] = (p[i - 1] * k) % mod;
            h[i] = (h[i - 1] * k + (string[i - 1] - 'a' + 1)) % mod;
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a1 = Integer.parseInt(st.nextToken());
            int b1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());


            boolean isEqual = false;
            if ((b1 - a1) == (b2 - a2)) {
                if (hash(a1, b1) == hash(a2, b2)) {
                    isEqual = true;
                }
            }

            pr.print(isEqual ? "Yes" : "No");

            if (i < n - 1) {
                pr.println();
            }
        }

        pr.flush();
        pr.close();
    }

    static long hash(int a, int b) {
        long res = (h[b] - h[a - 1] * p[b - a + 1]) % mod;
        if (res < 0) res += mod;
        return res;
    }
}
