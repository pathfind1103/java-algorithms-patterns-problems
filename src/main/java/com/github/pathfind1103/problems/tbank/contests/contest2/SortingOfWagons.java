package com.github.pathfind1103.problems.tbank.contests.contest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SortingOfWagons {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayDeque<Integer> wayOne = new ArrayDeque<>();
        Stack<Integer> deadEnd = new Stack<>();
        Stack<Integer> wayTwo = new Stack<>();

        StringBuilder sb = new StringBuilder();
        int pendingIn = 0;
        int pendingOut = 0;

        int current = 1;

        for (int i = 0; i < n; i++) {
            wayOne.addFirst(Integer.parseInt(st.nextToken()));
        }

        while (current <= n) {

            if (pendingOut > 0) {
                sb.append("2 ").append(pendingOut).append("\n");
                pendingOut = 0;
            }

            if (deadEnd.isEmpty()) {
                if (wayOne.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                deadEnd.push(wayOne.removeLast());
                pendingIn++;
            }

            // Завозим пока не появится current
            while (deadEnd.peek() != current) {
                if (!wayOne.isEmpty()) {
                    deadEnd.push(wayOne.removeLast());
                    pendingIn++;
                } else {
                    System.out.println(0);
                    return;
                }
            }

            if (pendingIn > 0) {
                sb.append("1 ").append(pendingIn).append("\n");
                pendingIn = 0;
            }

            while (!deadEnd.isEmpty() && deadEnd.peek() == current) {
                wayTwo.push(deadEnd.pop());
                pendingOut++;
                current++;
            }

            if (pendingOut > 0) {
                sb.append("2 ").append(pendingOut).append("\n");
                pendingOut = 0;
            }
        }

        String output = sb.toString().trim();

        if (output.isEmpty()) {
            System.out.println(0);
            return;
        }

        int lines = output.split("\n").length;

        System.out.println(lines);
        System.out.print(output);
    }
}