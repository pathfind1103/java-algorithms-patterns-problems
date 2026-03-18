package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MirrorCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String line = scanner.nextLine();
        String center = new String("");

        Map<Character, Integer> dict = new HashMap<>();

        // Создаем словарь частот
        for (char ch : line.toCharArray()) {
            if (dict.containsKey(ch)) {
              dict.put(ch, dict.get(ch) + 1);
            } else {
                dict.put(ch, 1);
            }
        }

        // Находим центр
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            int freq = dict.getOrDefault(ch, 0);

            if (freq % 2 == 1) {
                center = String.valueOf(ch);
                dict.put(ch, freq - 1);
                break;
            }
        }

        // Строим левую половину
        StringBuilder left = new StringBuilder();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            int freq = dict.getOrDefault(ch, 0);
            int pairs = freq / 2;
            for (int i = 0; i < pairs; i++) {
                left.append(ch);
            }
        }

        String leftStr = left.toString();
        String right = left.reverse().toString();

        String result = leftStr + center + right;

        System.out.println(result);
    }
}
