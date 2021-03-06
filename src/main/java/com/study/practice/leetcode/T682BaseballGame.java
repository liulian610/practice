package com.study.practice.leetcode;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * You're now a baseball game point recorder.
 *
 * Given a list of strings, each string can be one of the 4 following types:
 *
 * Integer (one round's score): Directly represents the number of points you get in this round.
 * "+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
 * "D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
 * "C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
 * Each round's operation is permanent and could have an impact on the round before and the round after.
 *
 * You need to return the sum of the points you could get in all the rounds.
 *
 * <p>
 * Example 1:
 * Input: ["5","2","C","D","+"]
 * Output: 30
 * Explanation:
 * Round 1: You could get 5 points. The sum is: 5.
 * Round 2: You could get 2 points. The sum is: 7.
 * Operation 1: The round 2's data was invalid. The sum is: 5.
 * Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
 * Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
 *
 * <p>
 * Example 2:
 * Input: ["5","-2","4","C","D","9","+","+"]
 * Output: 27
 * Explanation:
 * Round 1: You could get 5 points. The sum is: 5.
 * Round 2: You could get -2 points. The sum is: 3.
 * Round 3: You could get 4 points. The sum is: 7.
 * Operation 1: The round 3's data is invalid. The sum is: 3.
 * Round 4: You could get -4 points (the round 3's data has been removed). The sum is: -1.
 * Round 5: You could get 9 points. The sum is: 8.
 * Round 6: You could get -4 + 9 = 5 points. The sum is 13.
 * Round 7: You could get 9 + 5 = 14 points. The sum is 27.
 *
 * <p>
 * Note:
 * The size of the input list will be between 1 and 1000.
 * Every integer represented in the list will be between -30000 and 30000.
 *
 * @author dingdangss.
 * @since 2017-12-22 10:10.
 */
public class T682BaseballGame {

    /**
     * 做题前的思考:
     *  LinkedList vs Stack:
     *      LinkedList双向链表,插入和删除方便,但查询性能不如数组,能实现Stack的LIFO特性;
     *      Stack继承自Vector,使用数组实现,数组长度不够时,需要扩容。
     *  故此处使用LinkedList数据结构。
     */
    public int calPoints(String[] ops) {
        LinkedList<Integer> list = new LinkedList<>();
        int sum = 0;
        for (String op : ops) {
            switch (op) {
                case "C": {
                    int data = list.getLast();
                    list.removeLast();
                    sum -= data;
                    break;
                }
                case "D": {
                    int data = list.getLast() << 1;
                    list.add(data);
                    sum += data;
                    break;
                }
                case "+": {
                    int i = 0;
                    int data = 0;
                    for (ListIterator<Integer> it = list.listIterator(list.size()); it.hasPrevious() && i < 2; i ++) {
                        data += it.previous();
                    }
                    list.add(data);
                    sum += data;
                    break;
                }
                default: {
                    int data = Integer.valueOf(op);
                    list.add(data);
                    sum += data;
                    break;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        T682BaseballGame t682 = new T682BaseballGame();
        System.out.println("sum = " + t682.calPoints(new String[]{"5","2","C","D","+"}));
        System.out.println("sum = " + t682.calPoints(new String[]{"5","-2","4","C","D","9","+","+"}));
    }

}
