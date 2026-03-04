package com.github.pathfind1103.problems.tbank.contests.contest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class GoodDays {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] array = new int[n];
        int[] prefixSums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        long currentTotalSum = 0;
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            currentTotalSum += array[i];
            prefixSums[i] = (int) currentTotalSum;
        }

        int[] mins = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int value = array[i];

            if (stack.isEmpty()) {
                stack.push(i);
                mins[i] = i;
            } else {
                if (value < array[stack.peek()]) {
                    if (stack.size() == 1) {
                        stack.pop();
                        mins[i] = i;
                        stack.push(i);
                    } else {
                        while (value < array[stack.peek()]) {
                            stack.pop();
                        }

                        mins[i] = stack.peek();
                        stack.push(i);
                    }
                } else {
                    mins[i] = stack.peek();
                    stack.push(i);
                }
            }

        }

        Stack<Integer> maxR = new Stack<>();
        int[] maxRight = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int value = array[i];

            if (maxR.isEmpty()) {
                maxR.push(i);
                maxRight[i] = i;
            } else {
                if (value < array[maxR.peek()]) {
                    if (maxR.size() == 1) {
                        maxR.pop();
                        maxRight[i] = i;
                        maxR.push(i);
                    } else {
                        while (value < array[maxR.peek()]) {
                            if (maxR.size() == 1) {
                                maxR.pop();
                                maxRight[i] = i;
                                maxR.push(i);
                            } else {
                                maxR.pop();
                            }
                        }

                        maxRight[i] = maxR.peek();
                        maxR.push(i);
                    }
                } else {
                    maxRight[i] = maxR.peek();
                    maxR.push(i);
                }
            }
        }

        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(prefixSums));
        System.out.println(Arrays.toString(mins));
        System.out.println(Arrays.toString(maxRight));
        long maxRes = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int L = mins[i] + 1;
            int R = maxRight[i] - 1;

            long currentSum = prefixSums[R];
            if (L > 0) {
                currentSum -= prefixSums[L - 1];
            }

            long result = currentSum * array[i];

            if (result > maxRes) {
                maxRes = result;
            }
        }

        System.out.println(maxRes);
    }
}
