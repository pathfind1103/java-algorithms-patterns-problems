package com.github.pathfind1103.problems.tbank.contests.contest5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Random;
import java.util.StringTokenizer;

public class Anagrams2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int firstN = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] first = new int[firstN];
        for (int i = 0; i < firstN; i++) {
            first[i] = Integer.parseInt(st.nextToken());
        }

        int secondN = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] second = new int[secondN];
        for (int i = 0; i < secondN; i++) {
            second[i] = Integer.parseInt(st.nextToken());
        }

        long[] randomWeights = new long[100001];
        Random random = new Random();
        for (int i = 0; i < randomWeights.length; i++) {
            randomWeights[i] = random.nextLong();
        }

        HashSet<Long>[] fistHashes = new HashSet[firstN + 1];
        for (int i = 1; i <= firstN; i++) {
            fistHashes[i] = new HashSet<>();
        }

        for (int i = 0; i < firstN; i++) {
            long currentSum = 0;
            for (int j = i; j < firstN; j++) {
                currentSum += randomWeights[first[j]];
                int len = j - i + 1;
                fistHashes[len].add(currentSum);
            }
        }

        int maxLen = 0;
        for (int i = 0; i < secondN; i++) {
            long currentSum = 0;
            for (int j = i; j < secondN; j++) {
                currentSum += randomWeights[second[j]];
                int len = j - i + 1;

                if (len <= firstN && fistHashes[len].contains(currentSum)) {
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
            }
        }

        System.out.println(maxLen);
    }
}
