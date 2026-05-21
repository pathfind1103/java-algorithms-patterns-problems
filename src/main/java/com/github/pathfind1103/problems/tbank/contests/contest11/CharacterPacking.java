package com.github.pathfind1103.problems.tbank.contests.contest11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CharacterPacking {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null || s.isEmpty()) return;

        int n = s.length();
        String[][] dp = new String[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                String sub = s.substring(i, j + 1);

                dp[i][j] = sub;

                if (len <= 4) {
                    for (int k = i; k < j; k++) {
                        if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
                            dp[i][j] = dp[i][k] + dp[k + 1][j];
                        }
                    }
                    continue;
                }

                for (int k = i; k < j; k++) {
                    if (dp[i][k].length() + dp[k + 1][j].length() < dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                }

                for (int pLen = 1; pLen <= len / 2; pLen++) {
                    if (len % pLen == 0) {
                        int count = len / pLen;
                        String period = sub.substring(0, pLen);

                        if (isPeriodic(sub, period)) {
                            String compressed = count + "(" + dp[i][i + pLen - 1] + ")";
                            if (compressed.length() < dp[i][j].length()) {
                                dp[i][j] = compressed;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }

    private static boolean isPeriodic(String sub, String period) {
        int pLen = period.length();
        for (int i = 0; i < sub.length(); i++) {
            if (sub.charAt(i) != period.charAt(i % pLen)) {
                return false;
            }
        }
        return true;
    }
}
