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

        boolean flag = false;
        while (current != n) {
            if (flag) {
                break;
            }

            if (pendingOut > 0) {
                sb.append("2 ").append(pendingOut).append("\n");
                pendingOut = 0;
            }

            if (deadEnd.isEmpty()) {
                deadEnd.push(wayOne.removeLast());
                pendingIn += 1;
            }

            while (deadEnd.peek() != current) {
                if (!wayOne.isEmpty()) {
                    deadEnd.push(wayOne.removeLast());
                    pendingIn += 1;
                } else {
                    System.out.println(-1);
                    flag = true;
                    break;

                }
            }

            if (pendingIn > 0) {
                sb.append("1 ").append(pendingIn).append("\n");
                pendingIn = 0;
            }

            while (!deadEnd.isEmpty() && deadEnd.peek() == current) {
                wayTwo.push(deadEnd.pop());

                if (current != n) {
                    current += 1;
                    pendingOut += 1;
                }

//                System.out.println(current);
//                System.out.println(wayTwo.toString());

                if (pendingOut > 0) {
                    sb.append("2 ").append(pendingOut).append("\n");
                    pendingOut = 0;
                }
            }

            if (pendingIn > 0) {
                sb.append("1 ").append(pendingIn).append("\n");
            }

            if (pendingOut > 0) {
                sb.append("2 ").append(pendingOut).append("\n");
            }
        }

        System.out.print(sb.toString());
    }
}
