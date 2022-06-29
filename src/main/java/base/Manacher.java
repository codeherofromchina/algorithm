package base;

/**
 * 马拉车算法，求解字符串回文长度
 *
 * @Auther 王晓丹
 * @Date 2022/6/27 下午8:29
 */
public class Manacher {

    public static void main(String[] args) {
        String str = "abcbcba";
        int len = maxPalindromeLength(str);
        System.out.println(len);
    }

    /**
     * 求给定字符串的最大回文长度
     *
     * @param str
     * @return
     */
    public static int maxPalindromeLength(String str) {
        if (str == null) {
            return 0;
        }
        int answer = 0;
        char[] charArr = manacherStr(str);
        int len = charArr.length;
        int[] pLenArr = new int[len]; // 回文长度数组
        int cIndex = -1; // 回文中心点
        int rIndex = -1; // 回文长度右侧位置

        for (int i = 0; i < len; i++) {
            // 单前i位置的回文半径
            int curLen = i >= rIndex ? 1 : Math.max(rIndex - i, (cIndex << 1) - i);
            for (; i + curLen < len && i - curLen >= 0; curLen++) {
                if (charArr[i + curLen] != charArr[i - curLen]) {
                    break;
                }
            }
            pLenArr[i] = curLen;
            if (i + curLen > rIndex) {
                cIndex = i;
                rIndex = i + curLen;
            }
            answer = Math.max(answer, curLen - 1);
        }

        return answer;
    }


    /**
     * 字符串前后补充#填充
     *
     * @param str
     * @return
     */
    private static char[] manacherStr(String str) {
        int len = (str.length() << 1) + 1;
        char[] retCharArr = new char[len];
        for (int i = 0; i < len; i++) {
            if ((i & 1) != 0) {
                retCharArr[i] = str.charAt(i >> 1);
            } else {
                retCharArr[i] = '#';
            }
        }
        return retCharArr;
    }
}
