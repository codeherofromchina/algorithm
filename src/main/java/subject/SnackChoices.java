package subject;

/**
 * 选择零食
 *
 * @Auther 王晓丹
 * @Date 2022/7/7 上午6:16
 */
public class SnackChoices {

    public static void main(String[] args) {
        int[] v = {5, 3, 7};
        int capacity = 8;
        long star01 = System.currentTimeMillis();
        int way = process(v, 0, capacity);
        long star02 = System.currentTimeMillis();
        int way02 = process02(v, capacity);
        long end = System.currentTimeMillis();
        int way03 = process03(v, capacity);
        long end02 = System.currentTimeMillis();
        int way04 = process04(v, capacity);
        long end03 = System.currentTimeMillis();

        System.out.println(way + " cost" + (star02 - star01) + " ms");
        System.out.println(way02 + " cost" + (end - star02) + " ms");
        System.out.println(way03 + " cost" + (end02 - end) + " ms");
        System.out.println(way04 + " cost" + (end03 - end02) + " ms");
    }

    public static int process(int[] v, int i, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (i == v.length) {
            return 1;
        }

        int way = process(v, i + 1, rest);
        if (rest - v[i] >= 0) {
            way += process(v, i + 1, rest - v[i]);
        }
        return way;
    }

    public static int process02(int[] v, int capacity) {
        if (v == null || v.length == 0) {
            return 1;
        }
        int n = v.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            dp[n][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= capacity; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - v[i] >= 0) {
                    dp[i][j] += dp[i + 1][j - v[i]];
                }
            }
        }

        return dp[0][capacity];
    }

    /**
     * 0 ~ i 的零食整合达到 j大小的背包的数量
     *
     * @param v
     * @param capacity
     * @return
     */
    public static int process03(int[] v, int capacity) {
        if (v == null || v.length == 0) {
            return 1;
        }
        int n = v.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        int way = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1, k = 1 - v[i - 1]; j <= capacity; j++, k++) {
                dp[i][j] = dp[i - 1][j];

                dp[i][j] += (k >= 0 ? dp[i - 1][k] : 0);

                if (i == n) {
                    way += dp[i][j];
                }
            }
        }


        return way + 1;
    }


    /**
     * process03 空间压缩
     *
     * @param v
     * @param capacity
     * @return
     */
    public static int process04(int[] v, int capacity) {
        if (v == null || v.length == 0) {
            return 1;
        }
        int n = v.length;
        int[] dp = new int[capacity + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = capacity, k = capacity - v[i - 1]; k >= 0; j--, k--) {
                dp[j] += dp[k];
            }
        }

        int way = 0;
        for (int i = 0; i <= capacity; i++) {
            way += dp[i];
        }


        return way;
    }
}
