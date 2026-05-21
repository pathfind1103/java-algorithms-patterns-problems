package com.github.pathfind1103.problems.tbank.contests.contest10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class CrazyMonsters {
    static public LinkedList<Monster> monsters;

    public static class Monster {
        private long attack;
        private long defence;
        private boolean change;

        public Monster(long attack, long defence, boolean change) {
            this.attack = attack;
            this.defence = defence;
            this.change = change;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();

        StringTokenizer aString = new StringTokenizer(reader.readLine());
        StringTokenizer dString = new StringTokenizer(reader.readLine());

        int[] a = new int[n + 1];
        int[] d = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(aString.nextToken());
            d[i] = Integer.parseInt(dString.nextToken());
        }

        int[] l = new int[n + 1];
        int[] r = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            l[i] = i - 1;
            r[i] = i + 1;
        }
        l[1] = 0;
        r[n] = 0;

        Set<Integer> candidates = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            candidates.add(i);
        }

        for (int i = 0; i < n; i++) {
            Set<Integer> diedThisRound = new HashSet<>();

            for (Integer index : candidates) {
                long damage = 0;
                if (l[index] != 0) damage += a[l[index]];
                if (r[index] != 0) damage += a[r[index]];

                if (damage > d[index]) {
                    diedThisRound.add(index);
                }
            }

            sb.append(diedThisRound.size());
            sb.append(" ");

            Set<Integer> nextCandidates = new HashSet<>();
            for (Integer index : diedThisRound) {
                int left = l[index];
                int right = r[index];

                if (left != 0) {
                    r[left] = right;
                    nextCandidates.add(left);
                }

                if (right != 0) {
                    l[right] = left;
                    nextCandidates.add(right);
                }
            }

            nextCandidates.removeAll(diedThisRound);
            candidates = nextCandidates;
        }

        String result = sb.toString();
        System.out.println(result.trim());
    }
}
