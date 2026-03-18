package com.github.pathfind1103.problems.tbank.contests.contest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostfixEntry {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim().replace(" ", "");
        Pattern pattern = Pattern.compile("\\d");

        Stack<Integer> stack = new Stack<>();

        for (char ch : input.toCharArray()) {
            Matcher matcher = pattern.matcher((Character.toString(ch)));
            if (matcher.find()) {
                stack.push(Integer.parseInt(Character.toString(ch)));
            } else {
                if (ch == '+') {
                    int second = stack.pop();
                    int first = stack.pop();
                    int result = first + second;
                    stack.push(result);
                } else if (ch == '-') {
                    int second = stack.pop();
                    int first = stack.pop();
                    int result = first - second;
                    stack.push(result);
                } else if (ch == '*') {
                    int second = stack.pop();
                    int first = stack.pop();
                    int result = first * second;
                    stack.push(result);
                }
            }


        }

        System.out.println(stack.peek());
    }
}