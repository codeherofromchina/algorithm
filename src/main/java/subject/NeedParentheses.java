package subject;

/**
 * 判断括号字符串的有效性
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 上午8:31
 */
public class NeedParentheses {


    public static void main(String[] args) {
        String str = "()(())()()((()))";
        boolean validate = valid(str);
        if (validate) {
            System.out.println("正确的括号字符串");
        } else {
            int num = needParentheses(str);
            System.out.println("需要补充" + num + "个字符");
        }

        System.out.println(maxValiStr(str));
        System.out.println(maxDeep(str));
    }

    private static int needParentheses(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int retNum = 0;
        int pre = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                pre++;
            } else {
                pre--;
            }
            if (pre < 0) {
                retNum -= pre;
                pre = 0;
            }

        }
        return retNum + pre;
    }


    public static boolean valid(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        if ((str.length() & 1) != 0) {
            return false;
        }
        int pre = 0;
        char[] charArr = str.toCharArray();
        for (char c : charArr) {
            if (c == '(') {
                pre++;
            } else {
                pre--;
            }
            if (pre < 0) {
                return false;
            }
        }
        return pre == 0 ? true : false;
    }

    /**
     * 最长有效左右括号字符串长度
     *
     * @param str
     * @return
     */
    public static int maxValiStr(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int ans = 0;
        int len = str.length();
        char[] charArr = str.toCharArray();
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            if (charArr[i] == ')') {
                int index = i - dp[i - 1] - 1;
                if (index >= 0 && charArr[index] == '(') {
                    dp[i] = 2 + dp[i - 1] + (index > 0 ? dp[index - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static int maxDeep(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int ans = 0;
        int pre = 0;
        for (char c : str.toCharArray()) {
            pre += (c == '(' ? 1 : -1);
            ans = Math.max(ans, pre);
        }
        return ans;
    }
}
