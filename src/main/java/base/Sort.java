package base;

import java.util.Arrays;

/**
 * @Auther 王晓丹
 * @Date 2022/6/27 上午8:29
 */
public class Sort {


    public static void main(String[] args) {
        int[] nums = new int[]{5, 4, 5, 3, 2, 7, 12, 34, 6, 9};

        insertSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 在l ~ r 范围内做插入排序
     *
     * @param nums
     * @param l
     * @param r
     */
    public static void insertSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        for (int i = l + 1; i <= r; i++) {
            int index = i;
            while (index > l) {
                if (nums[index] < nums[index - 1]) {
                    ArrayHelp.swap(nums, index, --index);
                    continue;
                }
                break;
            }
        }
    }

    /**
     * 快排
     *
     * @param nums
     */
    public static void quickSort(int[] nums) {
        if (nums == null) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }


    private static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] patationIndex = quickPartition(nums, l, r);
        quickSort(nums, l, patationIndex[0] - 1);
        quickSort(nums, patationIndex[1] + 1, r);
    }

    /**
     * 荷兰国旗
     * 左右边界的下标都是属于中间数的下标
     *
     * @return int[0] 左边边界； int[1] 右侧边界
     */
    public static int[] quickPartition(int[] arr, int l, int r) {
        if (arr == null || arr.length == 0 || l < 0 || r >= arr.length || l > r) {
            // 基本过滤
            return null;
        }
        int num = arr[l];
        int mid = l + 1;
        while (mid <= r) {
            int cur = arr[mid];
            if (cur == num) {
                mid++;
            } else if (cur < num) {
                ArrayHelp.swap(arr, l++, mid++);
            } else {
                ArrayHelp.swap(arr, r--, mid);
            }
        }

        return new int[]{l, r};
    }

}
