package com.volkodav4ik;
/*
Условие задачи: https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 */

import java.util.Arrays;
import java.util.Random;

public class Task2 {
    private static final int MAX_JOB_DIFFICULTY = 1000;
    private static final int MAX_COUNT_OF_TASK = 300;
    private static final Random RANDOM = new Random();
    private static final int MAX_DAYS = 10;

    public static void main(String[] args) {
        int[] jobDifficulty = createAndInitArray();
        int d = RANDOM.nextInt(MAX_DAYS - 1) + 1;
        System.out.println(minDifficulty(jobDifficulty, d));

    }

    public static int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[] maxRight = new int[n + 1];
        maxRight[n] = jobDifficulty[n - 1];
        for (int i = n - 1; i > 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], jobDifficulty[i - 1]);
        }
        int[][] dp = new int[d + 1][n + 1];
        for (int i = 1; i <= d; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[1][0] = jobDifficulty[0];
        for (int j = 1; j <= n; j++) {
            dp[1][j] = Math.max(dp[1][j - 1], jobDifficulty[j - 1]);
        }
        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= n; j++) {
                int max = Integer.MIN_VALUE;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k - 1] + max);
                }
            }
        }
        return dp[d][n];
    }

    private static int[] createAndInitArray() {
        int[] arr = new int[RANDOM.nextInt(MAX_COUNT_OF_TASK - 1) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = RANDOM.nextInt(MAX_JOB_DIFFICULTY);
        }
        return arr;
    }
}
