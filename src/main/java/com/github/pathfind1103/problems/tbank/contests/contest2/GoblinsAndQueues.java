package com.github.pathfind1103.problems.tbank.contests.contest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class GoblinsAndQueues {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        StringTokenizer stringTokenizer;

        ArrayDeque<Integer> left = new ArrayDeque<>();
        ArrayDeque<Integer> right = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            char request = stringTokenizer.nextToken().charAt(0);

            switch (request) {
                case '+':
                    int id = Integer.parseInt(stringTokenizer.nextToken());
                    right.addLast(id);

                    if (left.size() < right.size()) {
                        left.addLast(right.removeFirst());
                    }

                    break;

                case '*':
                    int idd = Integer.parseInt(stringTokenizer.nextToken());
                    left.addLast(idd);

                    if (left.size() > right.size() + 1) {
                        right.addFirst(left.removeLast());
                    }

                    break;

                case '-':
                    pr.println(left.removeFirst());

                    if (left.size() < right.size()) {
                        left.addLast(right.removeFirst());
                    }

                    break;
            }
        }

        pr.flush();
    }
}
