package com.volkodav4ik;

import java.util.Arrays;
import java.util.Random;

/*
Условие задачи: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */
public class Main {
    private static final Random RANDOM = new Random();
    private static final int ARR_MAX_LENGTH = 500;
    private static final int ARR_MIN_LENGTH = 2;
    private static final int MAX_VALUE = 100;

    public static void main(String[] args) {
        int[] nums = createAndInitArray();
        System.out.println("Input array: " + Arrays.toString(nums));
        System.out.println("Output array: " + Arrays.toString(smallerNumbersThanCurrent(nums)));
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] output = new int[nums.length];
        int[] sortedCopy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedCopy);
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int count = 0;
            for (int value : sortedCopy) {
                if (current > value) {
                    count++;
                } else {
                    break;
                }
            }
            output[i] = count;
        }
        return output;
    }

    private static int[] createAndInitArray() {
        int[] arr = new int[RANDOM.nextInt(ARR_MAX_LENGTH - ARR_MIN_LENGTH) + ARR_MIN_LENGTH];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = RANDOM.nextInt(MAX_VALUE);
        }
        return arr;
    }
}
