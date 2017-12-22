package com.study.leetcode;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 *
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
 *
 * <p>
 * Example 1:
 * <p>
 * Input:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * <p>
 * Output:
 * Merged tree:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 * <p>
 * Note: The merging process must start from the root nodes of both trees.
 *
 * @author dingdangss
 * @since 2017-12-12 09:25.
 */
public class T617MergeTwoBinaryTrees {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        int t1Val = 0, t2Val = 0;
        TreeNode t1Left = null, t2Left = null, t1Right = null, t2Right = null;
        if (t1 != null) {
            t1Val = t1.val;
            t1Left = t1.left;
            t1Right = t1.right;
        }
        if (t2 != null) {
            t2Val = t2.val;
            t2Left = t2.left;
            t2Right = t2.right;
        }
        TreeNode newTree = new TreeNode(t1Val + t2Val);
        newTree.left = mergeTrees(t1Left, t2Left);
        newTree.right = mergeTrees(t1Right, t2Right);
        return newTree;
    }

    /**
     * 别人的更好的办法.
     */
    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if(t1 == null)
            return t2;

        if(t2 == null)
            return t1;

        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees1(t1.left, t2.left);
        node.right = mergeTrees1(t1.right, t2.right);
        return node;
    }
}
