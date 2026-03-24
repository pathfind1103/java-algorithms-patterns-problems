package com.github.pathfind1103.problems.tbank.contests.contest5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Editor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String preString = br.readLine();
            if (preString.isEmpty()) {
                System.out.println("0"); // Если подстрока пустая, вхождений 0
                continue;
            }

            int preLen = preString.length();
            String merged = preString + "#" + input;
            int[] z = zFunction(merged);

            int count = 0;
            StringBuilder indices = new StringBuilder();

            // Начинаем проверку со всей части после '#'
            for (int j = preLen + 1; j < z.length; j++) {
                if (z[j] == preLen) {
                    count++;
                    indices.append(j - (preLen + 1)).append(" ");
                }
            }

            // Сначала выводим количество, потом индексы
            if (count > 0) {
                System.out.println(count + " " + indices.toString().trim());
            } else {
                System.out.println("0");
            }
        }
    }

    public static int[] zFunction(String input) {
        char[] s = input.toCharArray();
        int n = s.length;
        int[] z = new int[n];
        int l = 0, r = 0;

        for (int i = 1; i < n; i++) {
            // Если мы внутри Z-блока, берем минимум из уже посчитанного и остатка блока
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }

            // Досчитываем наивно, пока символы совпадают
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                z[i]++;
            }

            // Если вышли за пределы текущего блока — обновляем L и R
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }
}
