package com.study.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard.
 *
 * <p>
 * Example 1:
 * Input: ["Hello", "Alaska", "Dad", "Peace"]
 * Output: ["Alaska", "Dad"]
 *
 * <p>
 * Note:
 * You may use one character in the keyboard more than once.
 * You may assume the input string will only contain letters of alphabet.
 *
 * @author dingdangss.
 * @since 2017-12-13 15:17.
 */
public class T500KeyboardRow {

    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        int[] rows = {2,3,3,2,1,2,2,2,1,2,2,2,3,3,1,1,1,1,2,1,1,3,1,3,1,3};
        outer : for (String word : words) {
            String wordLower = word.toLowerCase();
            char[] wchars = wordLower.toCharArray();
            int row = 0;
            for (char wchar : wchars) {
                int wcharRow = rows[wchar - 'a'];
                if (row == 0) {
                    row = wcharRow;
                } else if (wcharRow != row) {
                    continue outer;
                }
            }
            list.add(word);
        }
        return list.toArray(new String[0]);
    }

    public static void main(String[] args) {
        T500KeyboardRow t500 = new T500KeyboardRow();
        System.out.println(Arrays.toString(t500.findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
    }
}
