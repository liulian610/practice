package com.study.leetcode;

/**
 * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.
 *
 * <p>
 * The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.
 *
 * <p>
 * Example 1:
 * Input: "UD"
 * Output: true
 *
 * <p>
 * Example 2:
 * Input: "LL"
 * Output: false
 *
 * @author dingdangss
 * @since 2017-12-11 10:05.
 */
public class T657JudgeRouteCircle {

    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            switch (c) {
                case 'R' : x++; break;
                case 'L' : x--; break;
                case 'U' : y++; break;
                case 'D' : y--; break;
                default : break;
            }
        }
        return x == 0 && y == 0;
    }

    /**
     * 别人的更好的办法.
     *
     * 原理: 128个字符的ASCII码表:65～90为26个大写英文字母，97～122号为26个小写英文字母
     */
    public boolean judgeCircle1(String moves) {
        int temp[] = new int[128];
        for(char i:moves.toCharArray()){
            temp[i]++;
        }
        return (temp['R'] == temp['L'] && temp['U'] == temp['D']);
    }

    public static void main(String[] args) {
        T657JudgeRouteCircle t657 = new T657JudgeRouteCircle();
        System.out.println(t657.judgeCircle("RLUDUUDRLD"));
        System.out.println(t657.judgeCircle1("RLUDUUDRLD"));
    }
}
