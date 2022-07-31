package subject;

/**
 * 两个字符串的公共子序列
 *
 * @Auther 王晓丹
 * @Date 2022/7/7 上午11:00
 */
public class CommonSubsequence {

    public static void main(String[] args) {
        String str01 = "abc123acb";
        String str02 = "xy1z23adb";
        int len = process(str01, str02);
        int len02 = process01(str01, str02);
        System.out.println(len);
        System.out.println(len02);
    }

    /**
     * 公共子序列长度
     *
     * @param word01
     * @param word02
     * @return
     */
    public static int process(String word01, String word02) {
        if (word01 == null || word02 == null || word01.length() == 0 || word02.length() == 0) {
            return 0;
        }

        int len01 = word01.length();
        int len02 = word02.length();
        char[] str01 = word01.toCharArray();
        char[] str02 = word02.toCharArray();

        int[][] dp = new int[len01][len02];
        for (int i = 0; i < len02; i++) {
            dp[0][i] = str01[0] == str02[i] ? 1 : (i > 0 ? dp[0][i - 1] : 0);
        }
        for (int i = 1; i < len01; i++) {
            dp[i][0] = str01[i] == str02[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < len01; i++) {
            for (int j = 1; j < len02; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str01[i] == str02[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[len01 - 1][len02 - 1];
    }


    public static int process01(String word01, String word02) {
        if (word01 == null || word02 == null || word01.length() == 0 || word02.length() == 0) {
            return 0;
        }

        int len01 = word01.length();
        int len02 = word02.length();
        char[] str01 = word01.toCharArray();
        char[] str02 = word02.toCharArray();

        int[] dp = new int[len02];
        for (int i = 0; i < len02; i++) {
            dp[i] = str01[0] == str02[i] ? 1 : (i > 0 ? dp[i - 1] : 0);
        }

        int preOld = 0;
        for (int i = 1; i < len01; i++) {
            for (int j = 0; j < len02; j++) {
                if (j > 0) {
                    int tmp = Math.max(dp[j - 1], (str01[i] == str02[j] ? 1 + preOld : 0));
                    preOld = dp[j];
                    dp[j] = Math.max(dp[j], tmp);
                } else {
                    preOld = dp[j];
                    dp[j] = str01[i] == str02[j] ? 1 : dp[j];
                }
            }
        }
        return dp[len02 - 1];
    }
}
