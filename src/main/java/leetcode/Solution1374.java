package leetcode;

class Solution1374 {
    public static void main(String[] args) {
        System.out.println(generateTheString(7));
    }

    public static String generateTheString(int n) {
        if (n <= 0) {
            return null;
        }
        StringBuffer sBuffer = new StringBuffer();
        if ((n & 1) == 0) {
            n -= 1;
            sBuffer.append('b');
        }
        for (int i = 0; i < n; i++) {
            sBuffer.append('a');
        }
        return sBuffer.toString();
    }
}