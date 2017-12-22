package com.study.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * A self-dividing number is a number that is divisible by every digit it contains.
 *
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 *
 * Also, a self-dividing number is not allowed to contain the digit zero.
 *
 * Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.
 *
 * <p>
 * Example 1:
 * Input:
 * left = 1, right = 22
 * Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * Note:
 *
 * <p>
 * The boundaries of each input argument are 1 <= left <= right <= 10000.
 *
 * @author dingdangss
 * @since 2017-12-11 10:30.
 */
public class T728SelfDividingNumbers {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        outer: for (int i = left; i <= right; i++) {
            int tmp = i, last;
            do {
                last = tmp % 10;
                if (last == 0 || i % last != 0) {
                    continue outer;
                }
                tmp /= 10;
            } while (tmp > 0);
            list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        T728SelfDividingNumbers t728 = new T728SelfDividingNumbers();
        List<Integer> list = t728.selfDividingNumbers(1, 22);
        System.out.println(list.toString());
    }
}
