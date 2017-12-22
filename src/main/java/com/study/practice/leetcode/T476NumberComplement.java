package com.study.leetcode;

/**
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 *
 * <p>
 * Note:
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 *
 * <p>
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 *
 * <p>
 * Example 2:
 * Input: 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 *
 * @author dingdangss.
 * @since 2017-12-13 11:02.
 */
public class T476NumberComplement {

    public int findComplement(int num) {
        return num ^ ((1 << Integer.toBinaryString(num).length()) - 1);
    }

    /**
     * Integer.highestOneBit(num)取出num二进制中最高位的值
     */
    public int findComplement1(int num) {
        return num ^ ((Integer.highestOneBit(num) << 1) - 1);
    }

    /**
     * 别人的更好的办法.
     */
    public int findComplement2(int num) {
        return ~num & (Integer.highestOneBit(num) - 1);
    }

    public static void main(String[] args) {
        T476NumberComplement t476 = new T476NumberComplement();
        System.out.println(t476.findComplement(5));
        System.out.println(t476.findComplement(1));
        System.out.println(t476.findComplement(9));

        System.out.println(t476.findComplement1(5));
        System.out.println(t476.findComplement1(1));
        System.out.println(t476.findComplement1(9));

        System.out.println(t476.findComplement2(5));
        System.out.println(t476.findComplement2(1));
        System.out.println(t476.findComplement2(9));
    }
}
