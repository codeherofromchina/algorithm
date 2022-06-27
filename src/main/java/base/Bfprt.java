package base;

/**
 * bfprt算法求第k大的数
 *
 * @Auther 王晓丹
 * @Date 2022/6/27 上午8:58
 */
public class Bfprt {
    private static final int PARTITION_NUM = 5;

    public static void main(String[] args) {
        // 排完序后： [2, 3, 4, 5, 5, 6, 7, 9, 12, 34]
        int[] nums = new int[]{5, 4, 5, 3, 2, 7, 12, 34, 6, 9};
        for (int i = 0; i < nums.length; i++) {
            int numK = getNumK(nums, i);
            System.out.println("第" + i + "小的数：" + numK);
        }
    }

    /**
     * 获取第K小的数，k从0开始
     *
     * @param nums
     * @param k
     * @return
     */
    public static int getNumK(int[] nums, int k) {
        if (nums == null || k < 0) {
            throw new RuntimeException("param is error!");
        }
        return getNumK(nums, 0, nums.length - 1, k);
    }


    private static int getNumK(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        int midNum = getAboutMidNum(nums, l, r);
        int[] partitionIndex = partition(nums, l, r, midNum);
        if (k < partitionIndex[0]) {
            return getNumK(nums, l, partitionIndex[0] - 1, k);
        } else if (k > partitionIndex[1]) {
            return getNumK(nums, partitionIndex[1] + 1, r, k);
        }
        return nums[partitionIndex[0]];
    }


    /**
     * 荷兰国旗分区
     *
     * @param arr
     * @param l
     * @param r
     * @param num 给定的分区标准数
     * @return
     */
    private static int[] partition(int[] arr, int l, int r, int num) {
        int mid = l;
        while (mid <= r) {
            if (arr[mid] == num) {
                mid++;
            } else if (arr[mid] < num) {
                ArrayHelp.swap(arr, mid++, l++);
            } else {
                ArrayHelp.swap(arr, mid, r--);
            }
        }
        return new int[]{l, r};
    }

    /**
     * 精挑细选一个偏中间的数
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private static int getAboutMidNum(int[] nums, int l, int r) {
        int tmpArrLen = (r - l + PARTITION_NUM) / PARTITION_NUM;
        int[] tmpArr = new int[tmpArrLen];
        for (int i = 0, from = l; i < tmpArrLen; i++, from += 5) {
            int to = Math.min(r, from + PARTITION_NUM - 1);
            Sort.insertSort(nums, from, to);
            tmpArr[i] = nums[from + ((to - from) >> 1)];
        }
        return getNumK(tmpArr, 0, tmpArrLen - 1, (tmpArrLen -1) >> 1);
    }


}
