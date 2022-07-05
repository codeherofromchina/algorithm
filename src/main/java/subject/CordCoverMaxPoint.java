package subject;

/**
 * 长度为K的绳子能覆盖数组arr上多少个点
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 上午8:12
 */
public class CordCoverMaxPoint {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        int k = 4;
        int num = maxPoint(arr, k);
        System.out.println(num);
    }

    /**
     * @param arr 给定数组
     * @param k   绳子长度
     * @return
     */
    public static int maxPoint(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return 0;
        }
        int len = arr.length;
        int max = 0;
        int l = 0;
        int r = 0;
        while (l < len) {
            while (r < len && k >= arr[r] - arr[l]) {
                r++;
            }
            max = Math.max(max, r - l);
            l++;
        }
        return max;
    }
}
