package com.github.pathfind1103.problems.tbank.contests.contest4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SplittingTheArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long max = 0;
        long maxSums = 0;

        long[] array = new long[(int) n];

        for (int i = 0; i < n; i++) {
            long value = Long.parseLong(st.nextToken());
            array[i] = value;
            maxSums += value;

            if (value > max) {
                max = value;
            }
        }


        long l = max;
        long r = maxSums;
        long ans = maxSums;

        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (canDivide(mid, k, array)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean canDivide(long mid, long k, long[] array) {
        long times = 1;
        long sums = 0;

        for (int i = 0; i < array.length; i++) {
            if (sums + array[i] > mid) {
                times += 1;
                sums = array[i];
            } else {
                sums += array[i];
            }
        }
        
        if (times <= k) {
            return true;
        } else {
            return false;
        }
    }
}
