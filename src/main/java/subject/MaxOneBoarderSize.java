package subject;

/**
 * 矩阵中边框全是1的最大正方形边长
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 上午10:42
 */
public class MaxOneBoarderSize {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 1, 1}
        };

        int sideLen = getMaxSize(matrix);
        System.out.println(sideLen);

    }

    /**
     * 边框全是1的最大正方形边长
     *
     * @param matrix
     * @return
     */
    public static int getMaxSize(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] dp01 = new int[row][column];
        int[][] dp02 = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                dp01[i][j] = matrix[i][j] + (i > 0 ? dp01[i - 1][j] : 0);
                dp02[i][j] = matrix[i][j] + (j > 0 ? dp02[i][j - 1] : 0);
            }
        }

        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                int sideLen = Math.min(dp01[i][j], dp02[i][j]);
                for (int k = sideLen; k > ans; k--) {
                    // 从最长可能边长收集答案，小于答案的没必要收集
                    // 判断k边长是否能组成正方形边框
                    if (dp01[i][j - k + 1] >= k && dp02[i - k + 1][j] >= k) {
                        ans = k;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
