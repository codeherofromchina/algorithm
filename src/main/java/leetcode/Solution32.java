package leetcode;

class Solution32 {
    public static void main(String[] args) {
        String s = "(()";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int ans = process(str);
        reverse(str);
        int ans2 = process(str);
        return Math.max(ans, ans2);
    }

    private static int process(char[] str) {
        int ans = 0;
        int l = 0;
        int r = 0;
        int n = str.length;
        int s = 0;
        while (r < n) {
            if (str[r] == '(') {
                s++;
            } else if (str[r] == ')') {
                if (s == 0) {
                    r++;
                    l = r;
                    continue;
                }
                s--;
            }

            r++;
            if (s == 0) {
                ans = Math.max(ans, r - l);
            }

        }


        return ans;
    }

    private static void reverse(char[] str) {
        int l = 0;
        int r = str.length - 1;
        while (l < r) {
            char tmp = str[l];
            str[l++] = str[r] == '(' ? ')' : '(';
            str[r--] = tmp == '(' ? ')' : '(';
        }
        if (l == r) {
            str[l] = str[l] == '(' ? ')' : '(';
        }
    }
}