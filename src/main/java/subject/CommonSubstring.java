package subject;

/**
 * 最长公共子串
 *
 * @Auther 王晓丹
 * @Date 2022/7/7 上午11:41
 */
public class CommonSubstring {

    public static void main(String[] args) {
        String str01 = "abc123acb";
        String str02 = "xy1z23adb";
        int len = process(str01, str02);
        System.out.println(len);
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

        int ans = 0;
        int[] dp = new int[len02];
        for (int i = 0; i < len02; i++) {
            dp[i] = str01[0] == str02[i] ? 1 : 0;
            ans = Math.max(ans, dp[i]);
        }
        for (int i = 1; i < len01; i++) {
            dp[0] = str01[i] == str02[0] ? 1 : 0;
            ans = Math.max(ans, dp[0]);
            int pre = dp[0];
            for (int j = 1; j < len02; j++) {
                int tmp = str01[i] == str02[j] ? (pre + 1) : 0;
                pre = dp[j];
                dp[j] = tmp;
                ans = Math.max(ans, dp[j]);
            }
        }
        return ans;
    }


}
