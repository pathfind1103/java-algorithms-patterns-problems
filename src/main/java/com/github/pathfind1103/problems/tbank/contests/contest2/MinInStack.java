package com.github.pathfind1103.problems.tbank.contests.contest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MinInStack {
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<Integer> mins = new ArrayList<>();

    public MinInStack() {}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        MinInStack stack = new MinInStack();

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());

            if (operation == 1) {
                int value = Integer.parseInt(st.nextToken());
                stack.push(value);
            } else if (operation == 2) {
                stack.pop();
            } else if (operation == 3) {
                out.println(stack.getMin());
            }
        }

        out.flush();
        out.close();

    }

    public void push(int value) {
        values.add(value);

        if (mins.isEmpty() || value <= mins.get(mins.size() - 1)) {
            mins.add(value);
        }
    }

    public void pop() {
        if (!values.isEmpty()) {
            int removed = values.remove(values.size() - 1);
            if (removed == mins.get(mins.size() - 1)) {
                mins.remove(mins.size() -1);
            }
        }
    }

    public int getMin() {
        return mins.get(mins.size() - 1);
    }
}
