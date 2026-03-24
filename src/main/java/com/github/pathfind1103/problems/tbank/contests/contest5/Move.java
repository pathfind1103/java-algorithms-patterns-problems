package com.github.pathfind1103.problems.tbank.contests.contest5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Move {
    static int k = 31;
    static long mod = 1_000_000_007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        int count = 0;

        String s = b + b;
        int lenB = b.length();

        long[] hB = new long[s.length() + 1];
        long[] p = new long[s.length() + 1];

        p[0] = 1;

        for (int i = 1; i <= s.length(); i++) {
            p[i] = (p[i - 1] * k) % mod;
            hB[i] = (hB[i - 1] * k + s.charAt(i - 1)) % mod;
        }

        HashSet<Long> uniqueShifts = new HashSet<>();
        for (int i = 1; i <= lenB; i++) {
            long currentHash = (hB[i + lenB - 1] - hB[i - 1] * p[lenB]) % mod;
            if (currentHash < 0) {
                currentHash += mod;
            }

            uniqueShifts.add(currentHash);
        }

        long[] hA = new long[a.length() + 1];
        long[] pA = new long[a.length() + 1];

        pA[0] = 1;

        for (int i = 1; i <= a.length(); i++) {
            pA[i] = (pA[i - 1] * k) % mod;
            hA[i] = (hA[i - 1] * k + a.charAt(i - 1)) % mod;
        }

        for (int i = 1; i <= a.length() - lenB + 1; i++) {
            long substring = (hA[i + lenB - 1] - hA[i - 1] * pA[lenB]) % mod;

            if (substring < 0) {
                substring += mod;
            }

            if (uniqueShifts.contains(substring)) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static Long hash(String string) {
        char[] array = string.toCharArray();
        long[] p = new long[array.length];
        long hash = 0;

        p[0] = 1;
        hash += array[0] % mod;


        for (int i = 1; i < array.length; i++) {
            p[i] *= k;
            hash += (array[i] * p[i]) % mod;
        }

        return hash;
    }

    public static int[] zFunction(String input) {
        char[] s = input.toCharArray();
        int n = s.length;
        int[] z = new int[n];
        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }

            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                z[i]++;
            }

            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    static public String rotateLeft(String s, int k) {
        int n = s.length();
        k %= n;
        return s.substring(n - k) + s.substring(0, n - k);
    }
}
