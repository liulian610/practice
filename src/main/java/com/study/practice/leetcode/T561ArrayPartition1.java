package com.study.leetcode;

import java.util.Arrays;

/**
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 *
 * <p>
 * Example 1:
 * Input: [1,4,3,2]
 *
 * <p>
 * Output: 4
 * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
 *
 * <p>
 * Note:
 * n is a positive integer, which is in the range of [1, 10000].
 * All the integers in the array will be in the range of [-10000, 10000].
 *
 * @author dingdangss
 * @since 2017-12-12 13:25.
 */
public class T561ArrayPartition1 {

    /**
     * 思路:先快排,再取数组中每隔一个数字的总和.
     */
    public int arrayPairSum(int[] nums) {
        sort(nums, 0, nums.length - 1);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        Arrays.sort(nums);
        return sum;
    }

    /**
     * 别人的更好的办法.
     *
     * 思路:直接调用Arrays工具类的sort方法.
     */
    public int arrayPairSum1(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0; i < nums.length; i+=2){
            sum += nums[i];
        }
        return sum;
    }

    /**
     * 快速排序
     */
    private void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int i = left, j = right;
        int base = arr[i];
        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = base;
        sort(arr, left, i - 1);
        sort(arr, i + 1, right);
    }

    public static void main(String[] args) {
        T561ArrayPartition1 t561 = new T561ArrayPartition1();
        int arr[] = {3,1,4,56,2,4,64,34,243,54,6,4,54};
        t561.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
