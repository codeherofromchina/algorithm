package subject;

/**
 * 将数组划分为左右两部分，求两部分中的最大值差的绝对值
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 下午4:11
 */
public class ArrMaxNumSub {
    public static void main(String[] args) {
        int[] arr = {-1, -2, -3, -4, 8};

        int maxAbs = getMaxAbs(arr);
        System.out.println(maxAbs);
    }

    public static int getMaxAbs(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int n = arr.length;
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, arr[i]);
        }
        int min = Math.min(arr[0], arr[n - 1]);

        return max - min;
    }
}
