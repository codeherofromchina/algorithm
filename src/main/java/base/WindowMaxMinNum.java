package base;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 窗口内最大值和最小值
 *
 * @Auther 王晓丹
 * @Date 2022/6/26 上午11:01
 */
public class WindowMaxMinNum {


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int windowSize = 3;
        int[] ints = maxSlidingWindow(nums, windowSize);
        System.out.println(Arrays.toString(ints));

    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[][] ints = maxMinNum(nums, k);
        if (ints == null) {
            return new int[0];
        }
        return ints[0];
    }


    /**
     * 给定数组的窗口大小内，最大值和最小值
     *
     * @param arr
     * @param windowSize
     * @return arr[0] - 最大值列表   ； arr[1] - 最小值列表
     */
    public static int[][] maxMinNum(int[] arr, int windowSize) {
        if (arr == null || arr.length == 0 || windowSize < 1) {
            // 基本数据规范过滤
            return null;
        }
        int n = arr.length;
        if (windowSize >= n) {
            // 窗口很大，只能得出一个最大数和最小数，直接求数组最大最小值
            int[] maxMinNum = maxMinNum(arr);
            return new int[][]{{maxMinNum[0]}, {maxMinNum[1]}};
        }
        // 求窗口最大数的辅助队列，数据从头到尾降序
        LinkedList<Integer> maxQueue = new LinkedList<>();
        // 求窗口最小数的辅助队列，数据从头到尾升序
        LinkedList<Integer> minQueue = new LinkedList<>();

        int[][] ansArr = new int[2][n - windowSize + 1];

        for (int r = 0, l = 1 - windowSize; r < n; r++, l++) {
            while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] < arr[r]) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(r);
            while (!minQueue.isEmpty() && arr[minQueue.peekLast()] > arr[r]) {
                minQueue.pollLast();
            }
            minQueue.addLast(r);

            if (l >= 0) {
                int maxIndex = maxQueue.peekFirst();
                int minIndex = minQueue.peekFirst();
                ansArr[0][l] = arr[maxIndex];
                ansArr[1][l] = arr[minIndex];

                if (maxIndex == l) {
                    maxQueue.pollFirst();
                }
                if (minIndex == l) {
                    minQueue.pollFirst();
                }
            }
        }
        return ansArr;
    }

    /**
     * 数组的最大值和最小值
     *
     * @param arr
     * @return arr[0] - 最大值； arr[1] - 最小值
     */
    public static int[] maxMinNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int max = arr[0];
        int min = arr[0];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            max = arr[i] > max ? arr[i] : max;
            min = arr[i] < min ? arr[i] : min;
        }
        return new int[]{max, min};
    }
}
