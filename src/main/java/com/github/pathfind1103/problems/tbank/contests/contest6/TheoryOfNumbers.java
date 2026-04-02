package com.github.pathfind1103.problems.tbank.contests.contest6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class TheoryOfNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        int[] dist = new int[k];
        Arrays.fill(dist, Integer.MAX_VALUE);

        arrayDeque.add(1);
        dist[1] = 1;

        while (!arrayDeque.isEmpty()) {
            int top = arrayDeque.pollFirst();

            int free = (top * 10) % k;

            if (dist[free] > dist[top]) {
                dist[free] = dist[top];
                arrayDeque.addFirst(free);
            }

            int pay = (top + 1) % k;
            if (dist[pay] > dist[top] + 1) {
                dist[pay] = dist[top] + 1;
                arrayDeque.addLast(pay);
            }        }

        System.out.println(dist[0]);
    }
}
