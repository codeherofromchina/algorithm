package subject;

import base.ArrayHelp;
import base.LatelyGreaterOrLessNum;

/**
 * 子数组最小乘积的最大值
 *
 * @Auther 王晓丹
 * @Date 2022/6/26 下午5:08
 */
public class MaxSumMinProduct {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2};
        int i = maxSumMinProduct(nums);
        System.out.println(i);
    }

    /**
     * 通过枚举最小值类获取结果
     * 中间获取左右两边小于当前值的位置和累加和辅助数组
     *
     * @return
     */
    public static int maxSumMinProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        long[] sumArr = ArrayHelp.getSumArr(nums);
        int[][] lessThanIndexArr = LatelyGreaterOrLessNum.latelyLessThanNum(nums);

        long ansNum = 0;
        for (int i = 0; i < len; i++) {
            int r = lessThanIndexArr[i][1] == -1 ? len : lessThanIndexArr[i][1];
            long sum = sum(sumArr, lessThanIndexArr[i][0], r - 1);
            ansNum = Math.max(ansNum, sum * nums[i]);
        }
        return (int) (ansNum % MOD);
    }

    /**
     * l 和 r是 不包含在累加范围内的数组
     *
     * @param sumArr
     * @param l
     * @param r
     * @return
     */
    private static long sum(long[] sumArr, int l, int r) {
        return l < 0 ? sumArr[r] : (sumArr[r] - sumArr[l]);
    }
}
