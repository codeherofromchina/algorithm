package subject;

import java.util.PriorityQueue;

/**
 * 给定一个数组ar,已知其中所有的值都是非负的,将这个数组看作一个容器,
 * 请返回容器能装多少水比如,ar={3,1,2,5,2,4},
 * 根据值画出的直方图就是容器形状,
 * 该容器可以装下5格水再比如,a={4,5,1,3,2},.该容器可以装下2格水
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 下午4:39
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2};
        int water = water01(arr);
        System.out.println(water);


        int[][] arr02 = {

                {2, 2, 2, 2, 2},
                {2, 1, 1, 1, 2},
                {2, 2, 2, 2, 2}

        };
        int water02 = water02(arr02);
        System.out.println(water02);
    }


    /**
     * * 给定一个数组ar,已知其中所有的值都是非负的,将这个数组看作一个容器,
     * * 请返回容器能装多少水比如,ar={3,1,2,5,2,4},
     * * 根据值画出的直方图就是容器形状,
     * * 该容器可以装下5格水再比如,a={4,5,1,3,2},.该容器可以装下2格水
     *
     * @param arr
     * @return
     */
    public static int water01(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return 0;
        }
        int n = arr.length;
        int water = 0;
        int leftMax = arr[0];
        int rightMax = arr[n - 1];
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            if (leftMax < rightMax) {
                water += (leftMax - arr[l]);
                leftMax = Math.max(leftMax, arr[++l]);
            } else {
                water += (rightMax - arr[r]);
                rightMax = Math.max(rightMax, arr[--r]);
            }
        }
        return water;
    }

    /**
     * @param arr
     * @return
     */
    public static int water02(int[][] arr) {
        if (arr == null || arr.length <= 2) {
            return 0;
        }
        int row = arr.length;
        int column = arr[0].length;

        PriorityQueue<Pillar> priorityQueue = new PriorityQueue<>();

        boolean[][] initArr = new boolean[row][column];
        // 上下
        for (int i = 0; i < column; i++) {
            initArr[0][i] = true;
            initArr[row - 1][i] = true;
            priorityQueue.add(new Pillar(0, i, arr[0][i]));
            priorityQueue.add(new Pillar(row - 1, i, arr[row - 1][i]));
        }
        // 左右
        for (int i = 1; i < row - 1; i++) {
            initArr[i][0] = true;
            initArr[i][column - 1] = true;
            priorityQueue.add(new Pillar(i, 0, arr[i][0]));
            priorityQueue.add(new Pillar(i, column - 1, arr[i][column - 1]));
        }

        int maxNum = priorityQueue.peek().value;
        int water = 0;
        while (!priorityQueue.isEmpty()) {
            Pillar pillar = priorityQueue.poll();
            // 加入上下左右
            int i = pillar.row;
            int j = pillar.column;
            int num = maxNum;
            // 上
            if (i > 0 && !initArr[i - 1][j]) {
                int value = arr[i - 1][j];
                if (value < maxNum) {
                    water += (maxNum - value);
                }
                num = Math.min(num, value);
                initArr[i - 1][j] = true;
                priorityQueue.add(new Pillar(i - 1, j, value));
            }
            // 右
            if (j + 1 < column && !initArr[i][j + 1]) {
                int value = arr[i][j + 1];
                if (value < maxNum) {
                    water += (maxNum - value);
                }
                num = Math.min(num, value);
                initArr[i][j + 1] = true;
                priorityQueue.add(new Pillar(i, j + 1, value));
            }
            // 下
            if (i + 1 < row && !initArr[i + 1][j]) {
                int value = arr[i + 1][j];
                if (value < maxNum) {
                    water += (maxNum - value);
                }
                num = Math.min(num, value);
                initArr[i + 1][j] = true;
                priorityQueue.add(new Pillar(i + 1, j, value));
            }
            // 左
            if (j - 1 >= 0 && !initArr[i][j - 1]) {
                int value = arr[i][j - 1];
                if (value < maxNum) {
                    water += (maxNum - value);
                }
                num = Math.min(num, value);
                initArr[i][j - 1] = true;
                priorityQueue.add(new Pillar(i, j - 1, value));
            }

            if (num > maxNum) {
                maxNum = num;
            }
        }
        return water;
    }

    private static class Pillar implements Comparable<Pillar> {
        public Pillar(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }

        int row;
        int column;
        int value;

        @Override
        public int compareTo(Pillar pillar) {
            return Integer.compare(this.value, pillar.value);
        }
    }
}
