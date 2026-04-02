package com.github.pathfind1103.problems.tbank.contests.contest5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SubPalindromes {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder("#");

        for (char c : chars) {
            sb.append(c).append("#");
        }

        char[] newChars = sb.toString().toCharArray();

        int n = newChars.length;
        int[] d = new int[n];
        Arrays.fill(d, 1);
        int l = 0;
        int r = 0;

        for (int i = 0; i < n; i++) {
            if (i <= r) {
                int j = l + r - i;
                d[i] = Math.min(r - i + 1, d[j]);
            }

            while (i - d[i] >= 0 && i + d[i] < n && newChars[i - d[i]] == newChars[i + d[i]]) {
                d[i]++;
            }

            if (i + d[i] - 1 > r) {
                l = i - d[i] + 1;
                r = i + d[i] - 1;
            }
        }

        long count = 0;
        for (int i = 0; i < n; i++) {
            count += d[i] / 2;
        }
        System.out.println(count);
    }
}
