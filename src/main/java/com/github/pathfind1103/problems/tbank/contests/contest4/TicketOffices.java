package com.github.pathfind1103.problems.tbank.contests.contest4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TicketOffices {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> tree = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int aHour = Integer.parseInt(st.nextToken());
            int aMinute = Integer.parseInt(st.nextToken());
            int aSecond = Integer.parseInt(st.nextToken());
            int bHour = Integer.parseInt(st.nextToken());
            int bMinute = Integer.parseInt(st.nextToken());
            int bSecond = Integer.parseInt(st.nextToken());

            int aTime = aHour * 3600 + aMinute * 60 + aSecond;
            int bTime = bHour * 3600 + bMinute * 60 + bSecond;

            if (aTime < bTime) {
                tree.put(aTime, tree.getOrDefault(aTime, 0) + 1);
                tree.put(bTime, tree.getOrDefault(bTime, 0) - 1);
            } else if (aTime > bTime) {
                tree.put(0, tree.getOrDefault(0, 0) + 1);
                tree.put(bTime, tree.getOrDefault(bTime, 0) - 1);
                tree.put(aTime, tree.getOrDefault(aTime, 0) + 1);
                tree.put(86400, tree.getOrDefault(86400, 0) - 1);
            } else {
                tree.put(0, tree.getOrDefault(0, 0) + 1);
                tree.put(86400, tree.getOrDefault(86400, 0) - 1);
            }
        }

        int offices = 0;
        int result = 0;
        boolean flag = false;
        int start = 0;
        int end = 0;

        for (var entry : tree.entrySet()) {
            int time = entry.getKey();
            int operation = entry.getValue();

            offices += operation;

            if (offices == N) {
                if (flag == false) {
                    start = time;
                }
                flag = true;
            }

            if (offices < N && flag == true) {
                flag = false;
                end = time;
                result += end - start;
                end = 0;
                start = 0;
            }
        }

        System.out.println(result);
    }
}
