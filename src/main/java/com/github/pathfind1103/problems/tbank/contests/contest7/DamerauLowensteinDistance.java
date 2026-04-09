package com.github.pathfind1103.problems.tbank.contests.contest7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DamerauLowensteinDistance {
    public static int calculate(String str1, String str2) {
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            matrix[i][0] = i;
        }
        for (int j = 0; j <= str2.length(); j++) {
            matrix[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                int cost = (str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1;

                matrix[i][j] = Math.min(
                        matrix[i - 1][j] + 1,
                        Math.min(
                                matrix[i][j - 1] + 1,
                                matrix[i - 1][j - 1] + cost
                        )
                );

                if (i > 1 && j > 1 && str1.charAt(i - 1) == str2.charAt(j - 2) && str1.charAt(i - 2) == str2.charAt(j - 1)) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 2][j - 2] + cost);
                }
            }
        }

        return matrix[str1.length()][str2.length()];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int distance = calculate(str1, str2);
        System.out.println(distance);
    }
}
