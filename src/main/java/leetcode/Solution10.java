package leetcode;

class Solution10 {
    public static void main(String[] args) {
        String str = "aa";
        String pstr = "a*";

        System.out.println(isMatch(str, pstr));
    }

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null || s.length() == 0 || p.length() == 0) {
            return false;
        }
        return processDp(s.toCharArray(), p.toCharArray());
    }


    public static boolean processDp(char[] str, char[] pStr) {
        int n = str.length;
        int m = pStr.length;
        boolean[][] dp = new boolean[n + 1][m];
        dp[1][0] = str[0] == pStr[0] || pStr[0] == '.';
        for (int i = 1; i < m; i++) {
            if (pStr[i] == '*') {
                dp[0][i] = i - 2 >= 0 ? dp[0][i - 2] : true;
                continue;
            }
            dp[0][i] = false;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < m; j++) {
                if (pStr[j] == '*') {
                    dp[i][j] = j - 2 >= 0 ? dp[i][j - 2] || dp[i][j - 1] : dp[i][j - 1];
                    if ((pStr[j - 1] == str[i - 1] || pStr[j - 1] == '.')) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = (str[i - 1] == pStr[j] || pStr[j] == '.') && dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m - 1];
    }


    public static boolean process(char[] str, int index, char[] pStr, int index2) {
        if (pStr.length == index2) {
            return index == str.length && pStr.length == index2;
        } else if (index == str.length) {
            if (pStr[pStr.length - 1] != '*') {
                return false;
            }
            for (int i = index2 + 1; i < pStr.length; i += 2) {
                if (pStr[i] != '*') {
                    return false;
                }
            }
            return true;
        }
        if (pStr[index2] == '*') {
            return process(str, index, pStr, index2 + 1);
        }
        if (index2 + 1 < pStr.length && pStr[index2 + 1] == '*') {
            boolean flag = process(str, index, pStr, index2 + 2);
            if (str[index] == pStr[index2]) {
                return flag || process(str, index + 1, pStr, index2);
            }
            return flag;
        }

        if (str[index] == pStr[index2] || pStr[index2] == '.') {
            return process(str, index + 1, pStr, index2 + 1);
        }

        return false;
    }
}