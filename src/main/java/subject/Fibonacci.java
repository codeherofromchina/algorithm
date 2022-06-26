package subject;

import base.MatrixHelp;

/**
 * 斐波那契数列求解
 * 要求：答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1
 *
 * @Auther 王晓丹
 * @Date 2022/6/26 下午12:35
 */
public class Fibonacci {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        int n = 50;
        long t1 = System.currentTimeMillis();
        int ans01 = fib01(n);
        long t2 = System.currentTimeMillis();
        int ans02 = fib02(n);
        long t3 = System.currentTimeMillis();
        int ans03 = fib03(n);
        long t4 = System.currentTimeMillis();

        System.out.println("ans01:" + ans01 + ", cost " + (t2 - t1) + " ms");
        System.out.println("ans02:" + ans02 + ", cost " + (t3 - t2) + " ms");
        System.out.println("ans03:" + ans03 + ", cost " + (t4 - t3) + " ms");
    }

    /**
     * 正常公式解菲波那切数列
     *
     * @param n
     * @return
     */
    public static int fib01(int n) {
        if (n < 0) {
            return -1;
        }
        if (n < 2) {
            return n == 0 ? 0 : 1;
        }
        return (fib01(n - 1) + fib01(n - 2)) % MOD;
    }

    /**
     * 动态规划解斐波那契数列
     *
     * @param n
     * @return
     */
    public static int fib02(int n) {
        if (n < 0) {
            return -1;
        }
        if (n < 2) {
            return n == 0 ? 0 : 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        return dp[n];
    }

    /**
     * 用矩阵乘法求解菲波那切数列
     *
     * @param n
     * @return
     */
    public static int fib03(int n) {
        if (n < 0) {
            return -1;
        }
        if (n < 2) {
            return n == 0 ? 0 : 1;
        }
        // 菲波那切数列辅助矩阵
        long[][] matrix = new long[][]{{1, 1}, {1, 0}};

        long[][] powedMatrix = MatrixHelp.matrixPow(matrix, n - 1);
        long[][] retMatrix = MatrixHelp.matrixMultiple(new long[][]{{1, 0}}, powedMatrix);

        return (int) retMatrix[0][0];
    }
}
