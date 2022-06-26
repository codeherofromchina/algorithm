package subject;

/**
 * 中国象棋马跳步问题
 * 从一个点，跳转K步，跳到指定点，有多少种方法
 *
 * @Auther 王晓丹
 * @Date 2022/6/26 下午8:05
 */
public class ChineseChessJump {

    public static void main(String[] args) {
        int step01 = horseJump(0, 0, 2, 1, 1);
        int step02 = horseJump02(0, 0, 2, 1, 1);

        System.out.println(step01);
        System.out.println(step02);


    }

    /**
     * 从 srcX,srcY 跳转到 targetX,targetY 需要K步，有多少种方法
     * 中国象棋 10 * 9 的棋盘
     *
     * @param srcX
     * @param srcY
     * @param targetX
     * @param targetY
     * @param k
     * @return
     */
    public static int horseJump(int srcX, int srcY, int targetX, int targetY, int k) {
        if (!validate(srcX, srcY) || k < 0) {
            return 0;
        }
        if (k == 0) {
            return srcX == targetX && srcY == targetY ? 1 : 0;
        }

        int ans = 0;
        ans += horseJump(srcX - 2, srcY + 1, targetX, targetY, k - 1);
        ans += horseJump(srcX - 2, srcY - 1, targetX, targetY, k - 1);
        ans += horseJump(srcX - 1, srcY + 2, targetX, targetY, k - 1);
        ans += horseJump(srcX - 1, srcY - 2, targetX, targetY, k - 1);
        ans += horseJump(srcX + 1, srcY + 2, targetX, targetY, k - 1);
        ans += horseJump(srcX + 1, srcY - 2, targetX, targetY, k - 1);
        ans += horseJump(srcX + 2, srcY + 1, targetX, targetY, k - 1);
        ans += horseJump(srcX + 2, srcY - 1, targetX, targetY, k - 1);
        return ans;
    }


    public static int horseJump02(int srcX, int srcY, int targetX, int targetY, int k) {
        if (!validate(srcX, srcY) || k < 0) {
            return 0;
        }
        int[][][] dp = new int[k + 1][9][10];
        dp[0][srcX][srcY] = 1;

        for (int level = 1; level <= k; level++) { // 层遍历
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 10; y++) {
                    dp[level][x][y] += getValue(dp, x - 2, y + 1, k - 1);
                    dp[level][x][y] += getValue(dp, x - 2, y - 1, k - 1);
                    dp[level][x][y] += getValue(dp, x - 1, y + 2, k - 1);
                    dp[level][x][y] += getValue(dp, x - 1, y - 2, k - 1);
                    dp[level][x][y] += getValue(dp, x + 1, y + 2, k - 1);
                    dp[level][x][y] += getValue(dp, x + 1, y - 2, k - 1);
                    dp[level][x][y] += getValue(dp, x + 2, y + 1, k - 1);
                    dp[level][x][y] += getValue(dp, x + 2, y - 1, k - 1);
                }
            }
        }
        return dp[k][targetX][targetY];
    }


    public static int getValue(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        return dp[k][x][y];
    }

    /**
     * 验证下标没有越界
     *
     * @param srcX
     * @param srcY
     * @return
     */
    private static boolean validate(int srcX, int srcY) {
        if (srcX > 8 || srcY > 9 || srcX < 0 || srcY < 0) {
            return false;
        }
        return true;
    }
}
