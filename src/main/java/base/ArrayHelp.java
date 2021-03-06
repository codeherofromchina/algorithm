package base;

/**
 * 数组辅助类
 *
 * @Auther 王晓丹
 * @Date 2022/6/26 下午5:18
 */
public class ArrayHelp {

    /**
     * 获取数组的累加和数组
     *
     * @param arr
     * @return
     */
    public static int[] getSumArrMod(int[] arr, int mod) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return new int[0];
        }
        int len = arr.length;
        int[] retArr = new int[len];
        retArr[0] = arr[0] % mod;
        for (int i = 1; i < len; i++) {
            retArr[i] = (arr[i] % mod) + retArr[i - 1];
            retArr[i] %= mod;
        }
        return retArr;
    }

    /**
     * 获取数组的累加和数组
     *
     * @param arr
     * @return
     */
    public static long[] getSumArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return new long[0];
        }
        int len = arr.length;
        long[] retArr = new long[len];
        retArr[0] = arr[0];
        for (int i = 1; i < len; i++) {
            retArr[i] = arr[i] + retArr[i - 1];
        }
        return retArr;
    }


    /**
     * 获取数组的累加和数组
     *
     * @param arr
     * @return
     */
    public static long getSum(int[] arr, int l, int r) {
        if (arr == null || l > r) {
            return 0;
        }
        if (arr.length == 0) {
            return 0;
        }
        long ans = 0;
        l = l < 0 ? 0 : l;
        r = r >= arr.length ? arr.length - 1 : r;
        for (int i = l; i <= r; i++) {
            ans += arr[i];
        }
        return ans;
    }


    /**
     * 交换数组两个下标的位置
     *
     * @param arr
     * @param indexA
     * @param indexB
     */
    public static void swap(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }


    /**
     * 字节数组反转
     *
     * @param cArr
     * @return
     */
    public static char[] reverse(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        int len = cArr.length;
        char[] retCharArr = new char[len];
        for (int i = 0, j = len - 1; i < len; i++, j--) {
            retCharArr[i] = cArr[j];
        }
        return retCharArr;
    }
}
