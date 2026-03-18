package com.github.pathfind1103.problems.tbank.contests.contest4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MultiplicationЕable {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long l = 1;
        long r = (long) n * n;
        long answ = 0;

        while (l <= r) {
            long result = 0;
            long mid = l + (r - l) / 2;

            for (long i = 1; i <= n; i++) {
                result += Math.min(mid / i, n);
            }

            if (result >= k) {
                answ = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(answ);
    }
}