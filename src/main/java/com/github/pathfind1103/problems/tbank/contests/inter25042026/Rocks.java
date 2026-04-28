package com.github.pathfind1103.problems.tbank.contests.inter25042026;

import java.util.Scanner;

public class Rocks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.nextLine().trim();
        int lastDigit = Character.getNumericValue(n.charAt(n.length() - 1));

        if (lastDigit % 2 == 0) {
            System.out.println("Anya");
        } else {
            System.out.println("Masha");
        }
    }
}
