package leetcode;

class Solution50 {
    public static void main(String[] args) {
        int x = 2;
        int n = 10;
        System.out.println(myPow(x, n));
    }

    public static double myPow(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        double ans = 1;
        double base = x;
        int dig = 0;
        while (n != 0) {
            if ((n & (1 << dig)) != 0) {
                ans *= base;
                n ^= 1 << dig;
            }
            dig++;
            base *= base;
        }
        return ans;
    }


}