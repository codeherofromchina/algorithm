package leetcode;

class Solution8 {
    public static void main(String[] args) {
        String s = "   -42";
        int num = myAtoi(s);
        System.out.println(num);
    }

    public static int myAtoi(String s) {
        if (s == null) {
            return 0;
        }
        s = s.trim();
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int ans = 0;
        char[] str = s.toCharArray();
        boolean negFlag = str[0] == '-';
        int limit = negFlag ? Integer.MIN_VALUE / 10 : Integer.MAX_VALUE / 10;
        int index = str[0] == '-' || str[0] == '+' ? 1 : 0;
        while (index < n) {
            if (str[index] < '0' || str[index] > '9') {
                break;
            }
            if (negFlag && ans < limit) {
                return Integer.MIN_VALUE;
            }
            if (!negFlag && ans > limit) {
                return Integer.MAX_VALUE;
            }
            ans *= 10;
            int t = str[index] - '0';
            if (negFlag) {
                if (Integer.MIN_VALUE + t > ans) {
                    return Integer.MIN_VALUE;
                }
                ans -= t;
            } else {
                if (Integer.MAX_VALUE - t < ans) {
                    return Integer.MAX_VALUE;
                }
                ans += t;
            }

            index++;
        }

        return ans;
    }
}