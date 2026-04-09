package com.github.pathfind1103.problems.tbank.contests.contest7;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExplosionHazard {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] endsA = new int[n + 1];
        int[] endsSafe = new int[n + 1];

        endsA[1] = 1;
        endsSafe[1] = 2;

        for (int i = 2; i <= n; i++) {
                endsA[i] = endsSafe[i - 1];
                endsSafe[i] = (endsSafe[i - 1] + endsA[i - 1]) * 2;
            }

        System.out.print(endsSafe[n] + endsA[n]);
    }
}
