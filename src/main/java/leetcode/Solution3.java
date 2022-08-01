package leetcode;

class Solution3 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        if (n == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256];

        int ans = 0;
        int r = 0;
        int l = 0;

        while (r < n) {
            map[str[r] - 0]++;
            if (map[str[r] - 0] > 1) {
                // 缩小窗口
                while (l <= r) {
                    boolean breakFlag = map[str[l] - 0] == 2;
                    map[str[l] - 0]--;
                    l++;
                    if (breakFlag) {
                        break;
                    }
                }
            }
            r++;
            ans = Math.max(ans, r - l);
        }

        return ans;
    }
}