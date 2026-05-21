package com.github.pathfind1103.problems.yandex.sbd2026.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TaskB {
    static Map<String, String> parent = new HashMap<>();
    static Map<String, Integer> val = new HashMap<>();

    static String find(String i) {
        if (!parent.containsKey(i)) parent.put(i, i);
        if (parent.get(i).equals(i)) return i;
        parent.put(i, find(parent.get(i)));
        return parent.get(i);
    }

    static boolean union(String a, String b) {
        boolean aIsNum = a.matches("\\d+");
        boolean bIsNum = b.matches("\\d+");

        if (aIsNum && bIsNum) return a.equals(b);

        if (aIsNum) {
            String rootB = find(b);
            if (val.containsKey(rootB) && val.get(rootB) != Integer.parseInt(a)) return false;
            val.put(rootB, Integer.parseInt(a));
            return true;
        }

        if (bIsNum) return union(b, a);

        String rootA = find(a);
        String rootB = find(b);

        if (!rootA.equals(rootB)) {
            if (val.containsKey(rootA) && val.get(rootB) != null && !val.get(rootA).equals(val.get(rootB))) return false;
            parent.put(rootA, rootB);
            if (val.containsKey(rootA)) val.put(rootB, val.get(rootA));
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] s1 = br.readLine().split(" ");
        String[] s2 = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            if (!union(s1[i], s2[i])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
