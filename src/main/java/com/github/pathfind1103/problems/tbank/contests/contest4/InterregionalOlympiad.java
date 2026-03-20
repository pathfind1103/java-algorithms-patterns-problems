package com.github.pathfind1103.problems.tbank.contests.contest4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class InterregionalOlympiad {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        Task[] tasks = new Task[n];
        Integer[] result = new Integer[n];
        int resultt = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());

            tasks[i] = new Task(i, start, start + end);
        }

        Arrays.sort(tasks, (a, b) -> Long.compare(a.end, b.end));
        long currentTume = 0;
        int index = 0;

        for (Task task : tasks) {
            long time = task.start;

            if (time >= currentTume) {
                currentTume = task.end;
                resultt += 1;
                result[index] = task.id;
                index += 1;
            } else if (time < currentTume) {
                continue;
            }
        }

        System.out.println((long) resultt * c);
        System.out.println(resultt);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultt; i++) {
            sb.append(result[i] + 1).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static class Task {
        int id;
        long start;
        long end;

        public Task(int id, long start, long end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }
}
