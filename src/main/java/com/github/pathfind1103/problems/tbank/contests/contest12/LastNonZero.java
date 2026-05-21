package com.github.pathfind1103.problems.tbank.contests.contest12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LastNonZero {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;

        int n = Integer.parseInt(line.trim());

        if (n == 0 || n == 1) {
            System.out.println(1);
            return;
        }

        long current = 1;

        for (int i = 2; i <= n; i++) {
            current *= i;

            while (current % 10 == 0) {
                current /= 10;
            }

            current %= 100000;
        }

        System.out.println(current % 10);
    }
}
