package leetcode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).flag;

    }

    private static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int min = node.val;
        int max = node.val;
        boolean flag = true;

        if (leftInfo != null) {
            min = leftInfo.min;
            if (!leftInfo.flag || leftInfo.max >= node.val) {
                flag = false;
            }
        }

        if (rightInfo != null) {
            max = rightInfo.max;
            if (!rightInfo.flag || rightInfo.min <= node.val) {
                flag = false;
            }
        }
        return new Info(flag, min, max);
    }


    public static class Info {
        private boolean flag;
        private int min;
        private int max;

        public Info(boolean flag, int min, int max) {
            this.flag = flag;
            this.min = min;
            this.max = max;
        }

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}