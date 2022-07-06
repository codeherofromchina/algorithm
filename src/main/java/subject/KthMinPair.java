package subject;

import base.ArrayHelp;

import java.util.Arrays;

/**
 * 长度为N的数组a,一定可以组成N^2个数值对例如arr=3,1,2数值对有(3,3)(3,1)(3,2)(1,3)(1,1)(1,2)(2,3)(2,1)(2,2)
 * 也就是任意两个数都有数值对,而且自己和自己也算数值对数值对怎么排序?
 * 规定,第一维数据从小到大,第一维数据一样的,第二维数组也从小到大。
 * 所以上面的数值对排序的结果为(1,1)(1,2)(1,3)(2,1)(2,2)(2,3)(3,1)(3,2)(3,3)给定一个数组a,和整数k,
 * 返回第k小的数值对。
 *
 * @Auther 王晓丹
 * @Date 2022/7/6 上午10:00
 */
public class KthMinPair {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 4, 5, 5, 5};
        int[] numericalPair = kthMinPair(arr, 36);
        System.out.println(Arrays.toString(numericalPair));
    }

    /**
     * 返回第K个数值对
     *
     * @param arr
     * @param k   k从1开始
     * @return
     */
    public static int[] kthMinPair(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 1) {
            return null;
        }
        int n = arr.length;
        // 第一个数
        int firstIndex = (k + n - 1) / n - 1;
        int num01 = bfprt(arr, firstIndex);

        int lessThanNum = 0; // 小于第一个数的数量
        int repeatNum = 0; // 等于第一个数的数量
        for (int i = 0; i < n; i++) {
            if (arr[i] < num01) {
                lessThanNum++;
            } else if (arr[i] == num01) {
                repeatNum++;
            }
        }
        // 第二个数
        int secondIndex = ((k - lessThanNum * n) + repeatNum - 1) / repeatNum - 1;
        int num02 = bfprt(arr, secondIndex);
        // 返回
        return new int[]{num01, num02};
    }

    /**
     * bfprt获取数组第k小的数
     *
     * @param arr
     * @param k   从0开始
     * @return
     */
    private static int bfprt(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length) {
            throw new RuntimeException("参数错误");
        }
        return bfprt(arr, 0, arr.length - 1, k);
    }


    /**
     * k一定在l ~ r 之间
     *
     * @param arr
     * @param l
     * @param r
     * @param k
     * @return
     */
    private static int bfprt(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }
        int midNum = getMidNum(arr, l, r);
        int[] range = partition(arr, l, r, midNum);
        if (range[0] > k) {
            return bfprt(arr, l, range[0] - 1, k);
        } else if (range[1] < k) {
            return bfprt(arr, range[1] + 1, r, k);
        }
        return arr[range[0]];
    }


    private static int[] partition(int[] arr, int l, int r, int num) {
        int index = l;
        while (index < r) {
            if (arr[index] < num) {
                ArrayHelp.swap(arr, l++, index++);
            } else if (arr[index] > num) {
                ArrayHelp.swap(arr, index, r--);
            } else {
                index++;
            }
        }
        return new int[]{l, r};
    }

    /**
     * 获取模糊的中位数
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int getMidNum(int[] arr, int l, int r) {
        // 精挑细选一个'中位数'
        int group = (r - l + 5) / 5;
        int[] groupArr = new int[group];
        for (int i = 0, j = l; i < group; i++, j += 5) {
            groupArr[i] = insertSortRetMid(arr, j, Math.min(j + 4, r));
        }
        return bfprt(groupArr, 0, group - 1, (group - 1) >> 1);
    }

    /**
     * 插入排序
     * 并返回排序后的中位数
     *
     * @param arr
     * @param l
     * @param r
     */
    private static int insertSortRetMid(int[] arr, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            for (int j = i; j > l; j--) {
                if (arr[j] < arr[j - 1]) {
                    ArrayHelp.swap(arr, j, j - 1);
                }
            }
        }
        return arr[l + ((r - l) >> 1)];
    }


}
