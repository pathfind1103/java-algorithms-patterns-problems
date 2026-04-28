package com.github.pathfind1103.problems.tbank.contests.inter25042026;

import java.io.*;
import java.util.*;

public class Hiring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        long[][] candidates = new long[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            candidates[i][0] = Long.parseLong(st.nextToken());
            candidates[i][1] = Long.parseLong(st.nextToken());
        }

        // 1. Сортируем по разности (x - y) убыванию.
        // Слева те, кому выгоднее быть Backend, справа те, кому ML.
        Arrays.sort(candidates, (o1, o2) -> Long.compare(o2[0] - o2[1], o1[0] - o1[1]));

        // 2. Считаем префиксные суммы: лучший суммарный x для a человек на отрезке [0...i]
        long[] leftBestX = new long[n + 1];
        PriorityQueue<Long> pqX = new PriorityQueue<>(); // Храним x, удаляем самые маленькие
        long currentSumX = 0;
        for (int i = 0; i < n; i++) {
            currentSumX += candidates[i][0];
            pqX.add(candidates[i][0]);
            if (pqX.size() > a) {
                currentSumX -= pqX.poll();
            }
            leftBestX[i + 1] = (pqX.size() == a) ? currentSumX : -1;
        }

        // 3. Считаем суффиксные суммы: лучший суммарный y для b человек на отрезке [i...n-1]
        long[] rightBestY = new long[n + 2];
        PriorityQueue<Long> pqY = new PriorityQueue<>(); // Храним y, удаляем самые маленькие
        long currentSumY = 0;
        for (int i = n - 1; i >= 0; i--) {
            currentSumY += candidates[i][1];
            pqY.add(candidates[i][1]);
            if (pqY.size() > b) {
                currentSumY -= pqY.poll();
            }
            rightBestY[i + 1] = (pqY.size() == b) ? currentSumY : -1;
        }

        // 4. Ищем оптимальную точку раздела i.
        // Все, кто левее i, претендуют на Backend. Все, кто правее - на ML.
        long maxTotalSkill = 0;
        for (int i = a; i <= n - b; i++) {
            if (leftBestX[i] != -1 && rightBestY[i + 1] != -1) {
                maxTotalSkill = Math.max(maxTotalSkill, leftBestX[i] + rightBestY[i + 1]);
            }
        }

        System.out.println(maxTotalSkill);
    }
}
