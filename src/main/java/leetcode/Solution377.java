package leetcode;

class Solution377 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));

    }

    public static int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target < 0) {
            return 0;
        }

        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        int ans = 0;
        for (int i = 1; i <= target; i++) {
            if (i % nums[0] == 0) {
                dp[0][i] = 1;
            }
            if (i == target) {
                // ans += dp[0][i];
            }
        }


        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i] >= 0) {
                    dp[i][j] += dp[i][j - nums[i]];
                }
                if (j == target) {
                    ans += dp[i][j];
                }
            }
        }


        return ans;
    }
}