package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return list;
        }
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (0 < nums[i]) {
                // 相当于剪枝
                break;
            }
            int num = 0 - nums[i];
            for (int j = i + 1; j < n - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (num < nums[j]) {
                    break;
                }
                int num2 = num - nums[j];
                int k = n - 1;
                for (; k >= j + 1; k--) {
                    if (num2 > nums[k]) {
                        break;
                    }
                    if (num2 == nums[k]) {
                        // 收集答案
                        List<Integer> l = new ArrayList<>();
                        l.add(nums[i]);
                        l.add(nums[j]);
                        l.add(nums[k]);
                        list.add(l);
                        break;
                    }
                }
            }
        }
        return list;
    }

}