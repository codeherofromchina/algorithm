package leetcode;

class Solution6 {
    public static void main(String[] args) {
        String str = "PAYPALISHIRING";
        int numRows = 4;
        System.out.println(convert(str, numRows));
    }

    public static String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }

        char[] str = s.toCharArray();
        int n = s.length();

        StringBuffer retBuffer = new StringBuffer(n);

        for (int i = 0; i < numRows; i++) {
            int index = i;
            while (index < n) {
                retBuffer.append(str[index]);
                if (i != 0 && i != numRows - 1) {
                    // 中间，一个周期存在两个字符
                    int t = numRows - i;
                    if ((t << 1) + index - 2 < n) {
                        retBuffer.append(str[index + (t << 1) - 2]);
                    }
                }
                index += ((numRows << 1) - 2);
            }
        }
        return retBuffer.toString();
    }

}