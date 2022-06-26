package base;

import java.util.*;

/**
 * 数组内任意数左右两边最近的大于当前数，最近的小于当前数的位置
 *
 * @Auther 王晓丹
 * @Date 2022/6/26 上午11:49
 */
public class LatelyGreaterOrLessNum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 1, 1, 6, 5};
        int[][] greaterIndexArr = latelyGreaterThanNum(nums);
        for (int i = 0; i < greaterIndexArr.length; i++) {
            System.out.println(i + " -> " + Arrays.toString(greaterIndexArr[i]));
        }

        System.out.println("================");

        int[][] lessThanIndexArr = latelyLessThanNum(nums);
        for (int i = 0; i < lessThanIndexArr.length; i++) {
            System.out.println(i + " -> " + Arrays.toString(lessThanIndexArr[i]));
        }
    }

    /**
     * 求当前数左右两侧大于当前数的位置，如果没有，用-1表示
     *
     * @param arr
     * @return
     */
    public static int[][] latelyLessThanNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int n = arr.length;
        int[][] ansArr = new int[n][2];
        // 从底到顶，升序排列
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                // 弹出栈顶上的较大数据，保持从底到顶升序排列
                // 弹出的数据位置填写答案
                List<Integer> indexList = stack.pop();
                int left = -1;
                if (!stack.isEmpty()) {
                    List<Integer> peek = stack.peek();
                    left = peek.get(peek.size() - 1);
                }

                for (Integer index : indexList) {
                    ansArr[index][0] = left;
                    ansArr[index][1] = i;
                }
            }

            List<Integer> list;
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                list = stack.pop();
            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            stack.push(list);
        }

        while (!stack.isEmpty()) {
            List<Integer> indexList = stack.pop();
            int left = -1;
            if (!stack.isEmpty()) {
                List<Integer> list = stack.peek();
                left = list.get(list.size() - 1);
            }

            for (Integer index : indexList) {
                ansArr[index][0] = left;
                ansArr[index][1] = -1;
            }

        }
        return ansArr;
    }

    /**
     * 求左右两边大于当前数的最近位置，如果左右两边没有大于的数，用-1表示
     *
     * @param arr
     * @return
     */
    public static int[][] latelyGreaterThanNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int len = arr.length;
        // 声明结果数组
        int[][] ansArr = new int[len][2];
        // 当做栈来使用，栈底到栈顶降序排列
        LinkedList<List<Integer>> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] < arr[i]) {
                // 弹出时，填写当前弹出位置的答案
                List<Integer> indexs = stack.poll();
                int left = -1;
                if (!stack.isEmpty()) {
                    List<Integer> peek = stack.peek();
                    left = peek.get(peek.size() - 1);
                }
                for (Integer index : indexs) {
                    ansArr[index][0] = left;
                    ansArr[index][1] = i;
                }
            }
            List<Integer> list;
            if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)]) {
                list = stack.poll();
            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            stack.push(list);
        }
        while (!stack.isEmpty()) {
            // 弹出时，填写当前弹出位置的答案
            List<Integer> indexs = stack.poll();
            int left = -1;
            if (!stack.isEmpty()) {
                List<Integer> peek = stack.peek();
                left = peek.get(peek.size() - 1);
            }
            for (Integer index : indexs) {
                ansArr[index][0] = left;
                ansArr[index][1] = -1;
            }
        }
        return ansArr;
    }

}
