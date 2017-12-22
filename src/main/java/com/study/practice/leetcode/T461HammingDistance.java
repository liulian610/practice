package com.study.practice.leetcode;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, calculate the Hamming distance.
 *
 * Note:
 * 0 ≤ x, y < 231.
 *
 * Example:
 *
 * Input: x = 1, y = 4
 *
 * Output: 2
 *
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 *
 * The above arrows point to positions where the corresponding bits are different.
 *
 * <p>
 * HammingDistance 汉明距离.
 *
 * @author dingdangss
 * @since 2017-12-01 14:14.
 */
public class T461HammingDistance {

    /**
     * x,y异或后计算其二进制中1的个数:
     * 1、将该二进制与1做与运算,如果结果为1,则表示此二进制末位为1
     * 2、每右移一位判断末尾是否为1,直到右移到最终值=0结束
     */
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int distance = 0;
        while(z > 0) {
            if((z & 1) == 1) {
                distance ++;
            }
            z = z >> 1;
        }
        return distance;
    }

    /**
     * 别人的更好的办法1.
     *
     * 原理: 一个数和比它小1的数进行逻辑与,二进制中会少一个1;可因此计算二进制中1的数量.
     */
    public int hammingDistance1(int x, int y) {
        int z = x ^ y;
        int distance = 0;
        while (z != 0) {
            distance ++;
            z = z & (z - 1);
        }
        return distance;
    }

    /**
     * 别人的更好的办法2.
     *
     * TODO: Intgeger.bigCount的实现原理待研究
     */
    public int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public static void main(String[] args) {
        T461HammingDistance t461 = new T461HammingDistance();
        int distance = t461.hammingDistance(1, 4);
        System.out.println("distance = " + distance);
        int distance1 = t461.hammingDistance1(2, 4);
        System.out.println("distance1 = " + distance1);
        int distance2 = t461.hammingDistance2(2, 4);
        System.out.println("distance2 = " + distance2);
    }
}
