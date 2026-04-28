package com.github.pathfind1103.problems.tbank.contests.inter25042026;

import java.util.Scanner;

public class Cafe {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextLong()) return;
        long n = sc.nextLong();
        long m = sc.nextLong();

        if (m < 2) {
            System.out.println(0);
            return;
        }

        long mMod = m % MOD;
        long firstColumn = (mMod * ((mMod - 1 + MOD) % MOD)) % MOD;

        long mSq = (mMod * mMod) % MOD;
        long threeM = (3 * mMod) % MOD;
        long transition = (mSq - threeM + 3 + 2 * MOD) % MOD;

        long result = (firstColumn * power(transition, n - 1)) % MOD;

        System.out.println(result);
    }

    static long power(long base, long exp) {
        if (exp < 0) return 0;
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }
}
