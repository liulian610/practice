package com.study.leetcode;

import java.util.Arrays;

/**
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 * <p>
 * Example 1:
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 *
 * @author dingdangss.
 * @since 2017-12-13 13:50
 */
public class T557ReverseWordsInAString3 {

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(s.split(" ")).forEach(str -> {
            for (int i = str.length() - 1; i >= 0; i--) {
                sb.append(str.charAt(i));
            }
            sb.append(' ');
        });
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        T557ReverseWordsInAString3 t557 = new T557ReverseWordsInAString3();
        System.out.println(t557.reverseWords("Let's take LeetCode contest"));
    }
}
