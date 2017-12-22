package com.study.practice.sort;

import java.util.Arrays;

/**
 * 快速排序.
 *
 * @author dingdangss.
 * @since 2017-12-12 09:53
 */
public class QuickSort {

    public void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int realLeft = left;
        int realRight = right;
        int mid = left;
        boolean rightFlag = true;
        while (left < right) {
            if (rightFlag) {
                if (arr[right] < arr[mid]) {
                    arr[mid] = arr[mid] ^ arr[right];
                    arr[right] = arr[mid] ^ arr[right];
                    arr[mid] = arr[mid] ^ arr[right];
                    mid = right;
                    left++;
                    rightFlag = false;
                } else {
                    right--;
                }
            } else {
                if (arr[left] > arr[mid]) {
                    arr[mid] = arr[mid] ^ arr[left];
                    arr[left] = arr[mid] ^ arr[left];
                    arr[mid] = arr[mid] ^ arr[left];
                    mid = left;
                    right--;
                    rightFlag = true;
                } else {
                    left++;
                }
            }
        }
        sort(arr, realLeft, mid - 1);
        sort(arr, mid + 1, realRight);
    }

    /**
     * 别人的更好的办法.
     */
    public void sort1(int[] arr, int left, int right) {
        if (left >= right) return;
        int i = left, j = right;
        int mid = arr[i];
        while (i < j) {
            while (i < j && arr[j] >= mid) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] <= mid) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = mid;
        sort1(arr, left, i - 1);
        sort1(arr, i + 1, right);
    }

    public static void main(String[] args) {
        QuickSort s = new QuickSort();
        int arr[] = {3,1,4,56,2,4,64,34,243,54,6,4,54};
        s.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        int arr1[] = {3,1,4,56,2,4,64,34,243,54,6,4,54};
        s.sort1(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));
    }
}
