package base;

/**
 * 矩阵操作
 *
 * @Auther 王晓丹
 * @Date 2022/6/26 下午4:05
 */
public class MatrixHelp {

    /**
     * 矩阵乘法
     *
     * @param a
     * @param b
     * @return
     */
    public static long[][] matrixMultiple(long[][] a, long[][] b) {
        if (a == null || b == null || a.length == 0 || a[0].length != b.length) {
            return null;
        }
        int aRow = a.length;
        int aCol = b.length;
        int bCol = b[0].length;

        long[][] retMatrix = new long[aRow][bCol];
        for (int i = 0; i < aRow; i++) {
            for (int k = 0; k < bCol; k++) {
                for (int j = 0; j < aCol; j++) {
                    retMatrix[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        return retMatrix;
    }

    /**
     * 矩阵N次方
     *
     * @param a
     * @param n
     * @return
     */
    public static long[][] matrixPow(long[][] a, int n) {
        if (a == null || n < 0 || a.length == 0 || a.length != a[0].length) {
            // 基本条件过滤
            return null;
        }
        int len = a.length;
        long[][] base = new long[len][len];
        long[][] helpMatrix = new long[len][len];
        for (int i = 0; i < len; i++) {
            base[i][i] = 1;
            for (int j = 0; j < len; j++) {
                helpMatrix[i][j] = a[i][j];
            }
        }
        int num = 1;
        // 开始计算
        while (n != 0) {
            if ((n & num) != 0) {
                base = matrixMultiple(base, helpMatrix);
            }
            helpMatrix = matrixMultiple(helpMatrix, helpMatrix);
            n &= (~num);
            num <<= 1;
        }
        return base;
    }


}
