package leetcode;

class Solution41 {
    public static void main(String[] args) {
        int[] arr = {1, 1};
        int i = firstMissingPositive(arr);
        System.out.println(i);

    }

    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        int n = nums.length;

        int l = 0;
        int r = n - 1;

        while (l <= r) {
            if (l + 1 == nums[l]) {
                l++;
            } else if (nums[l] > n || nums[l] < 1 || nums[l] == nums[nums[l] - 1]) {
                int tmp = nums[r];
                nums[r--] = nums[l];
                nums[l] = tmp;
            } else {
                int tmp = nums[nums[l] - 1];
                nums[nums[l] - 1] = nums[l];
                nums[l] = tmp;
            }

        }

        for (; l < n; l++) {
            if (nums[l] != l + 1) {
                return l + 1;
            }
        }
        return l + 1;
    }
}