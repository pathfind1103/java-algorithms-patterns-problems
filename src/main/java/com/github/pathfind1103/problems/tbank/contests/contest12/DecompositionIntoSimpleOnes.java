package com.github.pathfind1103.problems.tbank.contests.contest12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class DecompositionIntoSimpleOnes {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;

        long n = Long.parseLong(line.trim());

        StringJoiner sj = new StringJoiner("*");

        if (n % 2 == 0) {
            int count = 0;
            while (n % 2 == 0) {
                count++;
                n /= 2;
            }
            appendFactor(sj, 2, count);
        }

        for (long d = 3; d * d <= n; d += 2) {
            if (n % d == 0) {
                int count = 0;
                while (n % d == 0) {
                    count++;
                    n /= d;
                }
                appendFactor(sj, d, count);
            }
        }

        if (n > 1) {
            appendFactor(sj, n, 1);
        }

        System.out.println(sj.toString());
    }

    private static void appendFactor(StringJoiner sj, long prime, int count) {
        if (count == 1) {
            sj.add(String.valueOf(prime));
        } else {
            sj.add(prime + "^" + count);
        }
    }
}
