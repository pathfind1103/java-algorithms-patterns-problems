package com.github.pathfind1103.problems.tbank.contests.contest5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimumShift {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String doubleInput = input + input;

        char[] s = doubleInput.toCharArray();
        int n = doubleInput.length();
        int i = 0;
        int j = 1;
        int k = 0;

        while (i < n && j < n && k < n) {
            char charI = s[(i + k) % n];
            char charJ = s[(j + k) % n];

            if (charI == charJ) {
                k++;
            } else {
                if (charI > charJ) {
                    i = i + k + 1;
                } else {
                    j = j + k + 1;
                }

                if (i == j) {
                    j ++;
                }

                k = 0;
            }
        }

        int start = Math.min(i, j);

        System.out.println(doubleInput.substring(start, start + input.length()));
    }
}
