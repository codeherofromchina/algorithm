package subject;

/**
 * 矩阵路径的最小累加和
 *
 * @Auther 王晓丹
 * @Date 2022/7/7 上午7:29
 */
public class MatrixMinSumPath {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {3, 5, 8, 100},
                {7, 9, 17, 101},
                {8, 12, 20, 102}
        };

        int sum = minSumPath(matrix);
        int sum02 = process(matrix);
        System.out.println(sum);
        System.out.println(sum02);
    }

    public static int minSumPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        return minSumPath(matrix, matrix.length - 1, matrix[0].length - 1, 0, 0);

    }


    public static int minSumPath(int[][] matrix, int m, int n, int i, int j) {
        if (i == m && j == n) {
            return matrix[m][n];
        }

        int sum = -1;
        if (i < m) {
            // 往下走
            sum = minSumPath(matrix, m, n, i + 1, j);
        }

        if (j < n) {
            int sum02 = minSumPath(matrix, m, n, i, j + 1);
            if (sum == -1) {
                sum = sum02;
            } else {
                sum = Math.min(sum, sum02);
            }
        }
        return sum + matrix[i][j];
    }


    public static int process(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int column = matrix[0].length;


        int[] dp = new int[column];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = column - 1; j >= 0; j--) {
                if (i + 1 < row && j + 1 < column) {
                    dp[j] = matrix[i][j] + Math.min(dp[j], dp[j + 1]);
                } else if (i + 1 < row) {
                    dp[j] += matrix[i][j];
                } else if (j + 1 < column) {
                    dp[j] = matrix[i][j] + dp[j + 1];
                } else {
                    dp[j] = matrix[i][j];
                }
            }
        }

        return dp[0];
    }
}
