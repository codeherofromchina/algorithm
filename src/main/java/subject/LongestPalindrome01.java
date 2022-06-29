package subject;

/**
 * 最长回文子串
 *
 * @Auther 王晓丹
 * @Date 2022/6/27 下午9:59
 */
public class LongestPalindrome01 {
    public static void main(String[] args) {
        String str = "babad";
        String s = longestPalindrome(str);
        System.out.println(s);
    }

    public static String longestPalindrome(String str) {
        if (str == null) {
            return null;
        }
        char[] charArr = manacherStr(str);
        int length = charArr.length;
        int[] pArr = new int[length];
        int cIndex = -1;
        int rIndex = -1;
        int index = -1;
        int maxLen = 0;
        for (int i = 0; i < length; i++) {
            int curC = i >= rIndex ? 1 : Math.max(pArr[(2 * cIndex - i)], rIndex - i);
            for (; i + curC < length && i - curC >= 0; curC++) {
                if (charArr[curC + i] != charArr[i - curC]) {
                    break;
                }
            }
            pArr[i] = curC;
            if (--curC > maxLen) {
                index = getOriStrIndex(curC, i);
                maxLen = curC;
            }
        }
        System.out.println(index + "  " + maxLen);
        return str.substring(index, index + maxLen);

    }


    public static char[] manacherStr(String str) {
        int length = str.length();
        char[] charArr = new char[(length << 1) + 1];
        for (int i = 0; i < charArr.length; i++) {
            if ((i & 1) == 0) {
                charArr[i] = '#';
            } else {
                charArr[i] = str.charAt(i >> 1);
            }
        }
        return charArr;
    }


    public static int getOriStrIndex(int palindromeLen, int curIndex) {
        if ((curIndex & 1) == 0) {
            curIndex++;
        }
        return (curIndex >> 1) - (palindromeLen >> 1);
    }


}
