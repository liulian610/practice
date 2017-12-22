package com.study.practice.sort;

import java.util.Arrays;

/**
 * 直接插入排序(属于 插入排序 分类).
 *
 * 基本思想:将一个记录插入到已排序好的有序表中，从而得到一个新的记录数增1的有序表。
 * 即：先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，直至整个序列有序为止。
 *
 * 直接插入排序是稳定的
 * 时间复杂度:O(n²),最坏O(n²),最好:O(n)
 * 空间复杂度:O(1)
 *
 * @author dingdangss.
 * @since  2017-12-04 09:52.
 */
public class StraightInsertionSort {

    private void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j + 1] < arr[j]) {
                    arr[j + 1] = arr[j + 1] ^ arr[j];
                    arr[j] = arr[j + 1] ^ arr[j];
                    arr[j + 1] = arr[j + 1] ^ arr[j];
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {3,1,4,56,2,4,64,34,243,54,6,4,54};
        StraightInsertionSort s = new StraightInsertionSort();
        s.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
