package com.github.pathfind1103.problems.tbank.contests.contest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Balls {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result = 0;

        ArrayDeque<Pair> arrayDeque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int color = Integer.parseInt(st.nextToken());
            boolean flag = false;

            if (arrayDeque.isEmpty() || arrayDeque.getLast().getColor() != color) {
                while (!arrayDeque.isEmpty() && arrayDeque.getLast().getAmount() >= 3) {
                    result += arrayDeque.getLast().getAmount();
                    arrayDeque.removeLast();

                    flag = true;
                }

                arrayDeque.add(new Pair(color, 1));

            } else {
                Pair top = arrayDeque.getLast();
                top.setAmount(top.getAmount() + 1);
            }

            if (i == n - 1) {
//                for (Pair pair : arrayDeque) {
//                    System.out.println(pair.toString());
//                }
                while (!arrayDeque.isEmpty() && arrayDeque.getLast().getAmount() >= 3) {
                    result += arrayDeque.getLast().getAmount();
                    arrayDeque.removeLast();
                }
            }

            if (flag == true) {
                Pair top = arrayDeque.getLast();
                arrayDeque.removeLast();

                if (top.getColor() == arrayDeque.getLast().getColor()) {
                    Pair newTop = arrayDeque.getLast();
                    newTop.setAmount(newTop.getAmount() + 1);
                } else {
                    arrayDeque.add(top);
                }

                flag = false;
            }
        }

        System.out.println(result);
    }

    public static class Pair {
        private int color;
        private int amount;

        public Pair(int color, int amount) {
            this.color = color;
            this.amount = amount;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "color=" + color +
                    ", amount=" + amount +
                    '}';
        }
    }
}
