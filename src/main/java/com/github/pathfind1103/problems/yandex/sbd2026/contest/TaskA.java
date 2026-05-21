package com.github.pathfind1103.problems.yandex.sbd2026.contest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TaskA {
    static class Word implements Comparable<Word> {
        String text;
        int count;

        Word(String text) {
            this.text = text;
            this.count = 0;
        }

        public int compareTo(Word other) {
            if (this.count != other.count) {
                return Integer.compare(this.count, other.count);
            }

            return this.text.compareTo(other.text);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Word>[] dictionary = new PriorityQueue[26];
        for (int i = 0; i < 26; i++) {
            dictionary[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < n; i++) {
            String s = br.readLine().trim();
            if (!s.isEmpty()) {
                int charIdx = s.charAt(0) - 'a';
                dictionary[charIdx].add(new Word(s));
            }
        }

        for (int i = 0; i < k; i++) {
            String query = br.readLine().trim();
            if (query.isEmpty()) {
                continue;
            }

            int charIdx = query.charAt(0) - 'a';
            PriorityQueue<Word> pq = dictionary[charIdx];

            Word best = pq.poll();
            out.println(best.text);

            best.count++;
            pq.add(best);
        }

        out.flush();
        out.close();
    }
}