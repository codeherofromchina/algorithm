package subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组累加和问题
 * 返回数组中，所有累加和为aim的，所有二元组
 * 返回数组中，所有累加和为aim的，所有三元组
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 下午9:50
 */
public class ArrSumAim {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 4, 5, 5, 5};
        int aim = 6;
        List<List<Integer>> lists = sum01(arr, aim);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
        System.out.println("============");
        lists = sum02(arr, aim);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }

    /**
     * 返回数组中，所有累加和为aim的，所有二元组
     *
     * @param arr
     * @param aim
     * @return
     */
    public static List<List<Integer>> sum01(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }
        return sumAim(arr, 0, arr.length - 1, aim);
    }


    public static List<List<Integer>> sum02(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> retList = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n - 2; i++) {
            List<List<Integer>> lists = sumAim(arr, i + 1, n - 1, aim - arr[i]);
            while (i < n - 2 && arr[i] == arr[i + 1]) {
                i++;
            }
            // 收集答案
            if (lists.size() > 0) {
                for (List<Integer> list : lists) {
                    list.add(0, arr[i]);
                }
                retList.addAll(lists);
            }
        }
        return retList;
    }

    /**
     * 返回给定区间数组中，所有累加和为aim的，所有二元组
     *
     * @param arr
     * @param l
     * @param r
     * @param aim
     * @return
     */
    public static List<List<Integer>> sumAim(int[] arr, int l, int r, int aim) {
        List<List<Integer>> retList = new ArrayList<>();
        if (l == r) {
            return retList;
        }

        while (l < r) {
            int num = arr[l] + arr[r];
            if (num == aim) {
                // 收集答案
                List<Integer> list = new ArrayList<>();
                list.add(arr[l]);
                list.add(arr[r]);
                retList.add(list);
                l++;
                while (l < r && arr[l - 1] == arr[l]) {
                    l++;
                }
            } else if (num < aim) {
                l++;
            } else {
                r--;
            }
        }
        return retList;

    }

}
