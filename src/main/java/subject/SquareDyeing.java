package subject;

/**
 * 正方形染色问题
 * 红色或者绿色
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 上午10:19
 */
public class SquareDyeing {
    public static void main(String[] args) {
        String str = "RGRRGGRGR";
        int num = dyeNum(str);
        System.out.println(num);
    }

    public static int dyeNum(String str) {
        if (str == null || str.length() <= 1) {
            return 0;
        }
        char[] charArray = str.toCharArray();
        int[] numArr = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            numArr[i] = (charArray[i] == 'R' ? 1 : 2);
        }

        return dyeNum(numArr);
    }

    /**
     * 需要染色几次，红色都比绿色靠近左侧
     * 1: 红色   2：绿色
     *
     * @param arr
     * @return
     */
    public static int dyeNum(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int totalRed = 0;
        for (int i : arr) {
            totalRed += (i == 1 ? 1 : 0);
        }
        int ans = arr.length;

        int blueNum = 0;
        int redNum = 0;
        for (int i = 0; i < arr.length; i++) {
            ans = Math.min(ans, totalRed - redNum + blueNum);
            if (arr[i] == 1) {
                redNum++;
            } else {
                blueNum++;
            }
        }
        ans = Math.min(ans, blueNum);

        return ans;
    }
}
