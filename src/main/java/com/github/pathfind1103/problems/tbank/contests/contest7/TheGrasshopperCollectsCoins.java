package com.github.pathfind1103.problems.tbank.contests.contest7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TheGrasshopperCollectsCoins {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] values = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1];
        dp[1] = values[1];
        ArrayDeque <Integer> deque = new ArrayDeque<>(k);
        deque.addLast(1);
        int[] parents = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            while (!deque.isEmpty() && deque.getFirst() < i - k) {
                deque.removeFirst();
            }

            parents[i] = deque.getFirst();
            dp[i] = dp[deque.getFirst()] + values[i];


            while (!deque.isEmpty() && dp[i] >= dp[deque.getLast()]) {
                deque.removeLast();
            }

            deque.addLast(i);
        }

        ArrayList<Integer> path = new ArrayList<>();
        int curr = n;
        while (curr != 0) {
            path.add(curr);
            if (curr == 1) break;
            curr = parents[curr];
        }

        java.util.Collections.reverse(path);

        System.out.println(dp[n]);
        System.out.println(path.size() - 1);

        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + (i == path.size() - 1 ? "" : " "));
        }
    }
}
