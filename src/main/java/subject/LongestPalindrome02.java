package subject;

import base.ArrayHelp;

/**
 * TODO 结果错误，需要改正
 * 由子序列构造的最长回文串的长度
 *
 * @Auther 王晓丹
 * @Date 2022/6/27 下午9:25
 */
public class LongestPalindrome02 {


    public static void main(String[] args) {
        String str01 = "afaaadacb";
        String str02 = "ca";
        int i = longestPalindrome(str01, str02);
        System.out.println(i);
    }


    /**
     * 两个字符串构子序列连接构造的最长回文长度
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int longestPalindrome(String word1, String word2) {
        if (word1 == null || "".equals(word1) || word2 == null || "".equals(word2)) {
            return 0;
        }
        int len01 = word1.length();
        int len02 = word2.length();
        char[] str01 = word1.toCharArray();
        char[] str02 = ArrayHelp.reverse(word2.toCharArray());
        int[][] dp = new int[len01][len02];
        int ans = 0;
        // 第一行
        for (int i = 0; i < len02; i++) {
            dp[0][i] = (str01[0] == str02[i] ? 1 : 0);
            ans = getMaxAnswer(len01, len02, ans, dp[0][i], 1, i + 1);
        }
        // 第一列
        for (int i = 0; i < len01; i++) {
            dp[i][0] = str01[i] == str02[0] ? 1 : 0;
            ans = getMaxAnswer(len01, len02, ans, dp[i][0], i + 1, 1);
        }
        // 从上往下，从左往右
        for (int i = 1; i < len01; i++) {
            for (int j = 1; j < len02; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str01[i] == str02[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
                ans = getMaxAnswer(len01, len02, ans, dp[i][j], i + 1, j + 1);
            }
        }

        return ans;
    }

    private static int getMaxAnswer(int lenR, int lenC, int oriAns, int dpValue, int row, int col) {
        if (dpValue == 0) {
            return oriAns;
        }
        dpValue <<= 1;
        dpValue += (row == lenR && col == lenC) ? 0 : 1;
        return Math.max(oriAns, dpValue);
    }
}
