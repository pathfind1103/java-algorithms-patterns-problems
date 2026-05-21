package com.github.pathfind1103.problems.tbank.contests.exam;

import java.io.*;
import java.util.*;

public class PeteAndForgottenPassword {
    static final long MOD = 1_000_000_009L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        boolean[] isPrime = new boolean[1000];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i < 1000; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < 1000; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        ArrayList<Integer>[] transitions = new ArrayList[100];
        for (int i = 0; i < 100; i++) {
            transitions[i] = new ArrayList<>();
        }

        long[] dp = new long[100];

        for (int number = 100; number <= 999; number++) {
            if (isPrime[number]) {
                int firstTwo = number / 10;
                int lastTwo = number % 100;

                transitions[firstTwo].add(lastTwo);
                dp[lastTwo]++;
            }
        }

        for (int len = 4; len <= n; len++) {
            long[] nextDp = new long[100];

            for (int state = 0; state < 100; state++) {
                if (dp[state] == 0) continue;

                for (int nextState : transitions[state]) {
                    nextDp[nextState] += dp[state];

                    if (nextDp[nextState] >= MOD) {
                        nextDp[nextState] %= MOD;
                    }
                }
            }

            dp = nextDp;
        }

        long answer = 0;
        for (int i = 0; i < 100; i++) {
            answer += dp[i];
            answer %= MOD;
        }

        System.out.println(answer);
    }
}