package base;

/**
 * 字符串查找算法
 *
 * @Auther 王晓丹
 * @Date 2022/6/27 上午10:41
 */
public class KMP {

    public static void main(String[] args) {
        String match = "abcabcabcdabdab";
        String str = "222abcabcabcdabdab1111";
        int index = search(str, match);
        System.out.println(index);
    }

    /**
     * 查找match是否在str字符串中
     *
     * @param str
     * @param match
     * @return 存在，返回对应下标位置；不存在返回-1
     */
    public static int search(String str, String match) {
        if (str == null || match == null || str.length() == 0 || match.length() == 0) {
            return -1;
        }
        int[] nextArr = getNextArr(match);
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();
        int strIndex = 0;
        int matchIndex = 0;
        while (strIndex < str.length() && matchIndex < match.length()) {
            if (matchIndex == -1 || strChars[strIndex] == matchChars[matchIndex]) {
                strIndex++;
                matchIndex++;
            } else {
                matchIndex = nextArr[matchIndex];
            }
        }
        return matchIndex == match.length() ? (strIndex - matchIndex) : -1;
    }


    /**
     * 获取匹配字符串下标
     *
     * @param match
     * @return
     */
    private static int[] getNextArr(String match) {
        int len = match.length();
        if (len == 1) {
            return new int[]{-1};
        }
        char[] str = match.toCharArray();
        int[] next = new int[len];
        next[0] = -1;
        next[1] = 0;
        int index = 0;
        for (int i = 2; i < len; i++) {
            while (index != -1 && str[index] != str[i - 1]) {
                index = next[index];
            }
            next[i] = ++index;
        }
        return next;
    }

}
