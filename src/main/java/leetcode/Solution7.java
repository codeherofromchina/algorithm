package leetcode;

class Solution7 {
    public static void main(String[] args) {
        int num = -123456789;
        System.out.println(num % 10);
        System.out.println(reverse(num));
    }

    public static int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = 0;
        int limit = x < 0 ? Integer.MIN_VALUE / 10 : Integer.MAX_VALUE / 10;


        while (x != 0) {
            if (x > 0 && ans > limit) {
                return 0;
            }
            if (x < 0 && ans < limit) {
                return 0;
            }
            ans *= 10;
            ans += (x % 10);
            x /= 10;
        }

        return ans;
    }
}