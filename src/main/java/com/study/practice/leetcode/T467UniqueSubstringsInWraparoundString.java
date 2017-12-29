package com.study.practice.leetcode;

import java.util.*;

/**
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 *
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
 *
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 *
 * <P>
 * Example 1:
 * Input: "a"
 * Output: 1
 * Explanation: Only the substring "a" of string "a" is in the string s.
 *
 * <P>
 * Example 2:
 * Input: "cac"
 * Output: 2
 * Explanation: There are two substrings "a", "c" of string "cac" in the string s.
 *
 * <p>
 * Example 3:
 * Input: "zab"
 * Output: 6
 * Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
 *
 * @author dingdangss.
 * @since 2017-12-28 09:29.
 */
public class T467UniqueSubstringsInWraparoundString {

    /**
     * 思路:将长度为1的子串存入boolean[]中,长度>1的子串存入HashSet中,利用set集合不能有重复元素的原理,最终计算boolean[]中值为true的数量+set集合的数量。
     */
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        boolean[] flags = new boolean[26];
        Set<String> set = new HashSet<>();
        char[] chars = p.toCharArray();
        flags[p.charAt(0) - 'a'] = true;
        int length = 1;
        int diff;
        for (int i = 1; i < chars.length; i++) {
            flags[chars[i] - 'a'] = true;
            if (((diff = (chars[i] - chars[i - 1])) == 1 || diff == -25)) {
                for (int j = i - length; j < i; j++) {
                    if (!set.add(new String(chars, j, i - j + 1))) {
                        break;
                    }
                }
                length ++;
            } else {
                length = 1;
            }
        }
        int sum = set.size();
        for (boolean flag : flags) {
            if (flag) {
                sum ++;
            }
        }
        return sum;
    }

    /**
     * 优化1:在上一个算法的基础上,将set替换成Map<Integer,boolean[26]>,其中key为子串长度,value为子串的开头字母对应存入boolean[26]数组。
     *
     * 本次优化比上一个算法节省内存空间,不需要将所有唯一子串都存入,只需存入子串开头字母及该子串的长度(开头字母+长度即能保证子串的唯一性)。
     */
    public int findSubstringInWraproundString1(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        boolean[] flags = new boolean[26];
        Map<Integer, boolean[]> map = new HashMap<>();
        char[] chars = p.toCharArray();
        flags[p.charAt(0) - 'a'] = true;
        int start = 0;
        int diff;
        for (int i = 1; i < chars.length; i++) {
            flags[chars[i] - 'a'] = true;
            if (((diff = (chars[i] - chars[i - 1])) == 1 || diff == -25)) {
                for (int j = start; j < i; j++) {
                    int length = i - j + 1;
                    boolean[] bs = map.get(length);
                    if (bs == null || bs.length == 0) {
                        bs = new boolean[26];
                        map.put(length, bs);
                    }
                    if (bs[chars[j] - 'a']){
                        break;
                    } else {
                        bs[chars[j] - 'a'] = true;
                    }
                }
            } else {
                start = i;
            }
        }
        int sum = count(flags);
        for (boolean[] fs : map.values()) {
            sum += count(fs);
        }
        return sum;
    }

    /**
     * 优化2:在优化1算法的基础上,合并boolean[]和Map<Integer,boolean[26]>为一个二维数组boolean[20000][26]。第一维表示子串长度,第二维表示字母对应位置的boolean值。
     *
     * 此方案有个漏洞就是当子串长度超过20000时,会出现数组长度溢出的问题。so,该方案只是优化思路的过渡方案,不可采用。
     */
    public int findSubstringInWraproundString2(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        boolean[][] bfs = new boolean[20000][26];
        char[] chars = p.toCharArray();
        bfs[1][p.charAt(0) - 'a'] = true;
        int start = 0;
        for (int i = 1; i < chars.length; i++) {
            char current = chars[i];
            bfs[1][current - 'a'] = true;
            int diff;
            if (((diff = (current - chars[i - 1])) == 1 || diff == -25)) {
                for (int j = start; j < i; j++) {
                    if (bfs[i - j + 1][chars[j] - 'a']) {
                        break;
                    } else {
                        bfs[i - j + 1][chars[j] - 'a'] = true;
                    }
                }
            } else {
                start = i;
            }
        }
        int sum = 0;
        for (boolean[] fs : bfs) {
            sum += count(fs);
        }
        return sum;
    }

    /**
     * 优化3:在优化2算法的基础上再优化,优化思路起源主要是想替换掉优化2算法的二维数组。
     *
     * 本次优化将boolean[20000][26]二维数组,替换成int[26]=count的表示方式,简化存储结构,并在思路上有个较大的转变。
     * 经过推敲,得出以下结论
     * 原理:子串个数等于以各字母开头的最长子串长度的总和
     * 如:zab,其各字母开头的最长子串有zab、ab、b;长度分别是3,2,1;则zab的子串个数有6个(具体子串如下:z、a、b、zab、za、ab)。
     * 如:xyzzab,其各字母开头的最长子串有xyz、yz、zab、ab、b,长度分别是3,2,3,2,1;则xyzzab的子串个数有11个(具体子串如下:x、y、z、xy、yz、xyz、a、b、za、ab、zab)。
     * 具体实现:在遍历字符串p的各个字符时,当出现有效子串时,按子串开头字母找到对应在int[26]中的值,取该值和当前子串长度的较大值。最终计算int[26]数组中各位置上的数值总和。
     * 时间复杂度:O(nlogn)
     */
    public int findSubstringInWraproundString3(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        int[] counts = new int[26];
        char[] chars = p.toCharArray();
        counts[p.charAt(0) - 'a'] = 1;
        int length = 1;
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            counts[c - 'a'] = Math.max(counts[c - 'a'], 1);
            int diff;
            if (((diff = (c - chars[i - 1])) == 1 || diff == -25)) {
                length++;
                if (counts[chars[i - length + 1] - 'a'] >= length) {
                    continue;
                }
                for (int j = i - length + 1; j < i; j++) {
                    counts[chars[j] - 'a'] = Math.max(counts[chars[j] - 'a'], i - j + 1);
                }
            } else {
                length = 1;
            }
        }
        int sum = 0;
        for (int count : counts) {
            sum += count;
        }
        return sum;
    }

    /**
     * 优化4:原理同优化3算法。目的为达到O(n)的时间复杂度。
     *
     * 发现到的规律:当存在xyz子串时,一定存在yz、z子串,假设以x开头的最长子串就是xyz,长度为3;那么以y开头的最长子串长度一定大于等于yz的长度,即xyz长度-1。
     * 得出结论:假设以a-z开头的最长子串长度存于int[26] arr数组中。则arr[next] = Math.max(arr[next], arr[current] - 1);
     * 时间复杂度:O(n)
     */
    public int findSubstringInWraproundString4(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        // 存放以a-z开头的子串最大长度
        int[] counts = new int[26];
        char[] chars = p.toCharArray();
        // 初始化首位数据
        int startIndex = p.charAt(0) - 'a';
        counts[startIndex] = 1;
        int length = 1;
        // 遍历p中各个字符,计算以a-z开头的最大子串的长度
        int diff;
        for (int i = 1; i < chars.length; i++) {
            if (((diff = (chars[i] - chars[i - 1])) == 1 || diff == -25)) {
                length++;
            } else {
                startIndex = chars[i] - 'a';
                length = 1;
            }
            counts[startIndex] = Math.max(counts[startIndex], length);
        }
        // 取以a-z开头的子串中最大长度的数组下标
        int maxCountIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] >= counts[maxCountIndex]) maxCountIndex = i;
        }
        // 从最大长度的数组下标开始遍历数组,arr[next] = Math.max(arr[next], arr[current] - 1);
        int current, next;
        for (int i = maxCountIndex; i < maxCountIndex + 26; i++) {
            current = i % 26;
            next = (i + 1) % 26;
            counts[next] = Math.max(counts[next], counts[current] - 1);
        }
        // 计算所有子串数量
        int sum = 0;
        for (int count : counts) {
            sum += count;
        }
        return sum;
    }

    /**
     * 优化5:参考别人的思路。和优化3/4的思路差不多,但存入int[26]的不是以a-z开头的最长子串长度,而是以a-z结尾的最长子串长度。
     *
     * 这种方式就不需要像优化4算法那样,在第一次遍历之后,还要对int[]数组中的各数值再做一次arr[next] = Math.max(arr[next], arr[current] - 1);的处理。
     * 时间复杂度:O(n)
     */
    public int findSubstringInWraproundString5(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        // 存放以a-z开头的子串最大长度
        int[] counts = new int[26];
        char[] chars = p.toCharArray();
        // 初始化首位数据
        counts[chars[0] - 'a'] = 1;
        int length = 1;
        // 遍历p中各个字符,计算以a-z结尾的最大子串的长度
        int diff;
        for (int i = 1; i < chars.length; i++) {
            if (((diff = (chars[i] - chars[i - 1])) == 1 || diff == -25)) {
                length++;
            } else {
                length = 1;
            }
            int index = chars[i] - 'a';
            if (counts[index] < length) {
                counts[index] = length;
            }
        }
        // 计算所有子串数量
        int sum = 0;
        for (int count : counts) {
            sum += count;
        }
        return sum;
    }

    private int count(boolean[] flags) {
        int sum = 0;
        for (boolean flag : flags) {
            if (flag) sum++;
        }
        return sum;
    }

    public static void main(String[] args) {
        T467UniqueSubstringsInWraparoundString t467 = new T467UniqueSubstringsInWraparoundString();
        System.out.println(t467.findSubstringInWraproundString5("a")); // 1
        System.out.println(t467.findSubstringInWraproundString5("cac")); // 2
        System.out.println(t467.findSubstringInWraproundString5("zab")); // 6
        System.out.println(t467.findSubstringInWraproundString5("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz")); // 34151
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            t467.findSubstringInWraproundString5("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
        }
        System.out.println(System.currentTimeMillis() - time);
    }

}
