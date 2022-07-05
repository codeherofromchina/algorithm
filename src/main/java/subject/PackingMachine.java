package subject;

/**
 * 有n个打包机器从左到右一字排开,上方有一个自动装置会抓取一批放物品到每个打包机上,
 * 放到每个机器上的这些物品数量有多有少,由于物品数量不相同,
 * 需要工人将每个机器上的物品进行移动从而到达物品数量相等才能打包。
 * 每个物品重量太大、每次只能搬一个物品进行移动,为了省力,只在相邻的机器上移动。
 * 请计算在搬动最小轮数的前提下,使每个机器上的物品数量相等。
 * 如果不能使每个机器上的物品相同,返回-1。
 * 例如[1.05表示有3个机器,每个机器上分别有1、0、5个物品,
 * 经过这些轮后第一轮:10<-5=>114第二轮:1<-1<-4=>213第三轮:21<-3=>222移动了3轮,每个机器上的物品相等,所以返回3
 * 例如2,2,3]表示有3个机器,每个机器上分别有2、2、3个物品,这些物品不管怎么移动,都不能使三个机器上物品数量相等,返回-1
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 下午3:20
 */
public class PackingMachine {

    public static void main(String[] args) {
        int[] arr = {5, 10, 15, 30};
        int calc = minOps(arr);
        System.out.println(calc);
    }

    /**
     * 计算需要移动的轮数
     *
     * @param arr
     * @return
     */
    public static int minOps(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int len = arr.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += arr[i];
        }
        int avg = sum / len;
        if (avg * len != sum) {
            // 不能平均，返回-1结果
            return -1;
        }
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int leftNum = leftSum - i * avg;
            int rightNum = (sum - leftSum - arr[i]) - (len - 1 - i) * avg;
            if (leftNum < 0 && rightNum < 0) {
                ans = Math.max(ans, -(leftNum + rightNum));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftNum), Math.abs(rightNum)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}
