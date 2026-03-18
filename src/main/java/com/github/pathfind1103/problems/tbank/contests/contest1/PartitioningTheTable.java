package com.github.pathfind1103.problems.tbank.contests.contest1;

import java.util.Scanner;

public class PartitioningTheTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        for (int test = 0; test < t; test++) {
            long n = scanner.nextLong();
            long m = scanner.nextLong();

            long totalCells = n * m;
            long totalSum = totalCells * (totalCells + 1) / 2;

            long bestVDiff = Long.MAX_VALUE;
            long bestV = -1;

            long left = 1;
            long right = m + 1;

            while (left <= right) {
                long mid = left + (right - left) / 2;
                long k = mid - 1;

                long leftSum = n * k * (k + 1 + (n - 1) * m) / 2;

                long diff = Math.abs(2 * leftSum - totalSum);

                if (diff < bestVDiff || (diff == bestVDiff && mid < bestV)) {
                    bestVDiff = diff;
                    bestV = mid;
                }

                if (leftSum < totalSum / 2) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            long bestHDiff = Long.MAX_VALUE;
            long bestH = -1;

            left = 1;
            right = n + 1;

            while (left <= right) {
                long mid = left + (right - left) / 2;
                long k = (mid - 1) * m;

                long upperSum = k * (k + 1) / 2;

                long diff = Math.abs(2 * upperSum - totalSum);

                if (diff < bestHDiff || (diff == bestHDiff && mid < bestH)) {
                    bestHDiff = diff;
                    bestH = mid;
                }

                if (upperSum < totalSum / 2) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            if (bestVDiff < bestHDiff) {
                System.out.println("V " + bestV);
            } else if (bestHDiff < bestVDiff) {
                System.out.println("H " + bestH);
            } else {
                if (bestV != -1) {
                    System.out.println("V " + bestV);
                } else {
                    System.out.println("H " + bestH);
                }
            }
        }

        scanner.close();
    }
}