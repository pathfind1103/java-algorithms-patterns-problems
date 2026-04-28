package com.github.pathfind1103.problems.tbank.contests.inter25042026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine().trim();
        if (line == null) return;
        char[] input = line.toCharArray();

        int countStart = 0;
        while (countStart < input.length && input[countStart] == 'a') {
            countStart++;
        }

        int countEnd = 0;
        int k = input.length - 1;
        while (k >= 0 && input[k] == 'a') {
            countEnd++;
            k--;
        }

        if (countStart > countEnd) {
            System.out.println("No");
            return;
        }

        if (isPalindrome(trimA(input))) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static boolean isPalindrome(char[] string) {
        if (string.length <= 1) {
            return true;
        }

        int j = string.length - 1;
        for (int i = 0; i < string.length / 2; i++) {
            if (string[i] != string[j - i]) {
                return false;
            }
        }

        return true;
    }

    public static char[] trimA(char[] input) {
        int start = 0;
        int end = input.length - 1;

        while (start <= end && input[start] == 'a') {
            start++;
        }

        while (end >= start && input[end] == 'a') {
            end--;
        }

        if (start > end) {
            return new char[0];
        }

        int newSize = end - start + 1;
        char[] result = new char[newSize];
        System.arraycopy(input, start, result, 0, newSize);

        return result;
    }
}
