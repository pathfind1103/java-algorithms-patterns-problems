package com.github.pathfind1103.problems.tbank.contests.contest5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AnInaccurateMatch {
    static int k = 31;
    static long mod = 1_000_000_007L;
    static long[] hP;
    static long[] pP;
    static long[] hT;
    static long[] pT;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pString = br.readLine();
        String tString = br.readLine();

        hP = new long[pString.length() + 1];
        pP = new long[pString.length() + 1];

        pP[0] = 1;

        for (int i = 1; i <= pString.length(); i++) {
            pP[i] = (pP[i - 1] * k) % mod;
            hP[i] = (hP[i - 1] * k + pString.charAt(i - 1)) % mod;
        }

        hT = new long[tString.length() + 1];
        pT = new long[tString.length() + 1];

        pT[0] = 1;

        for (int i = 1; i <= tString.length(); i++) {
            pT[i] = (pT[i - 1] * k) % mod;
            hT[i] = (hT[i - 1] * k + tString.charAt(i - 1)) % mod;
        }

        List<Integer> result = new ArrayList<>();
        int n = pString.length();
        int m = tString.length();

        for (int i = 0; i <= m - n; i++) {
            int pos1 = findLCP(0, i, n);

            if (pos1 == n) {
                result.add(i + 1);
            } else {
                int startP2 = pos1 + 1;
                int startT2 = i + pos1 + 1;
                int newLen = n - startP2;

                int pos2 = 0;

                if (newLen > 0) {
                    pos2 = findLCP(startP2, startT2, newLen);
                }

                if (pos1 + 1 + pos2 >= n) {
                    result.add(i + 1);
                }
            }
        }

        System.out.println(result.size());
        for (Integer integ : result) {
            System.out.print(integ + " ");
        }

    }

    public static int findLCP(int startP, int startT, int maxLen) {
        int l = 1;
        int r = maxLen;
        int ans = 0;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            long hashP = (hP[mid + startP] - hP[startP] * pP[mid]) % mod;
            if (hashP < 0) hashP += mod;

            long hashT = (hT[mid + startT] - hT[startT] * pT[mid]) % mod;
            if (hashT < 0) hashT += mod;

            if (hashT == hashP) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }
}
