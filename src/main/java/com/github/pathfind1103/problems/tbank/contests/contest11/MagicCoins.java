package com.github.pathfind1103.problems.tbank.contests.contest11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MagicCoins {
    static int m;
    static long n;
    static long[] allCoins;
    static List<Long> bestCombination = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        long[] nominations = new long[m];
        long totalSum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            nominations[i] = Long.parseLong(st.nextToken());
            totalSum += nominations[i] * 2;
        }

        if (n > totalSum) {
            System.out.println(-1);
            return;
        }

        allCoins = new long[2 * m];
        for (int i = 0; i < m; i++) {
            allCoins[2 * i] = nominations[i];
            allCoins[2 * i + 1] = nominations[i];
        }

        findCombination(0, 0, new ArrayList<>());

        if (bestCombination != null) {
            System.out.println(bestCombination.size());
            Collections.sort(bestCombination);
            for (long coin : bestCombination) {
                System.out.print(coin + " ");
            }
            System.out.println();
        } else {
            System.out.println(0);
        }
    }

    private static void findCombination(int idx, long currentSum, List<Long> currentList) {
        if (currentSum == n) {
            if (bestCombination == null || currentList.size() < bestCombination.size()) {
                bestCombination = new ArrayList<>(currentList);
            }
            return;
        }

        if (currentSum > n || idx == allCoins.length) {
            return;
        }
        if (bestCombination != null && currentList.size() >= bestCombination.size()) {
            return;
        }

        currentList.add(allCoins[idx]);
        findCombination(idx + 1, currentSum + allCoins[idx], currentList);
        currentList.remove(currentList.size() - 1); // Откат (Backtracking)

        findCombination(idx + 1, currentSum, currentList);
    }
}
