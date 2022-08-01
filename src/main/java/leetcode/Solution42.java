package leetcode;

class Solution42 {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        return process(height);
    }


    private static int process(int[] height) {
        int l = 0;
        int r = height.length - 1;

        int ans = 0;
        int leftMax = 0;
        int rightMax = 0;
        while (l <= r) {
            if (height[l] < height[r]) {
                ans += leftMax > height[l] ? (leftMax - height[l]) : 0;
                leftMax = leftMax > height[l] ? leftMax : height[l];
                l++;
            } else {
                ans += rightMax > height[r] ? (rightMax - height[r]) : 0;
                rightMax = rightMax > height[r] ? rightMax : height[r];
                r--;
            }
        }

        return ans;
    }

}