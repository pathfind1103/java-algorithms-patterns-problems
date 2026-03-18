package com.github.pathfind1103.problems.tbank.contests.contest4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CowsInTheStalls {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] coords = new long[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            long coord = Long.parseLong(st.nextToken());
            coords[i] = coord;
        }

        long l = 0;
        long r = coords[n - 1] - coords[0];
        long ans = 0;

        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (canPlace(mid, coords, k)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean canPlace(long mid, long[] coords, long k) {
        long lastPos = coords[0];
        long cows = 1;

        for (int i = 0; i < coords.length; i++) {
            if (coords[i] - lastPos >= mid) {
                cows++;
                lastPos = coords[i];
            }
        }

        if (cows >= k) {
            return true;
        }

        return false;
    }
}
