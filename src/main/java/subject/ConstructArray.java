package subject;

import java.util.Arrays;

/**
 * 给定正整数M，构造一个长度为M的数组，要求 i j k 位置， i< j < k , 使得 arr[i] + arr[k] != 2*arr[j]
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 上午11:55
 */
public class ConstructArray {
    public static void main(String[] args) {
        int[] arr = makeNo(5);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] makeNo(int m) {
        if (m <= 0) {
            return new int[0];
        }
        int iter = 0;
        int num = 1;
        while (num < m) {
            num <<= 1;
            iter++;
        }

        int[] retArr = new int[m];
        retArr[0] = 1;
        for (int i = 0, j = 2; i < iter; i++, j <<= 1) {
            int midIndex = (j - 1) >> 1;
            for (int k = j - 1; midIndex >= 0; midIndex--, k--) {
                if (k < m) {
                    retArr[k] = retArr[midIndex] << 1;
                }
                retArr[midIndex] = (retArr[midIndex] << 1) - 1;
            }
        }

        return retArr;
    }
}
