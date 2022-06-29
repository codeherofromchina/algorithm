package subject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 简化版俄罗斯方块的最高高度
 *
 * @Auther 王晓丹
 * @Date 2022/6/29 下午9:41
 */
public class TetrisMaxHeight {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2}, {5, 1}, {3, 2}, {3, 3}};
        int[] maxHeightArr = maxHeight(arr);
        System.out.println(Arrays.toString(maxHeightArr));


    }

    /**
     * 方块数组，
     *
     * @param arr
     * @return
     */
    public static int[] maxHeight(int[][] arr) {
        if (arr == null) {
            return null;
        }
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            treeSet.add(arr[i][0]);
            treeSet.add(arr[i][0] + arr[i][1] - 1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        int size = 1;
        for (Integer num : treeSet) {
            map.put(num, size++);
        }

        int[] retArr = new int[arr.length];
        SegmentTree segmentTree = new SegmentTree(size);
        for (int i = 0; i < arr.length; i++) {
            int from = map.get(arr[i][0]);
            int to = map.get(arr[i][0] + arr[i][1] - 1);
            int height = segmentTree.maxHeight(from, to);
            segmentTree.update(from, to, height + arr[i][1]);
            retArr[i] = segmentTree.maxHeight(0, size - 1);
        }
        return retArr;
    }

    private static class SegmentTree {
        int size;
        int[] treeArr;
        int[] update;

        public SegmentTree(int size) {
            this.size = size;
            int len = (size << 1) + 1;
            treeArr = new int[len];
            update = new int[len];
        }

        public int maxHeight(int l, int r) {
            checkRangeIndex(l, r);
            return maxHeight(0, 0, size - 1, l, r);
        }


        public void update(int l, int r, int v) {
            checkRangeIndex(l, r);
            update(0, 0, size - 1, l, r, v);
        }

        private void update(int index, int tL, int tR, int l, int r, int v) {
            if (l <= tL && tR <= r) {
                treeArr[index] = v;
                update[index] = v;
                return;
            }

            pushDown(index, tL, tR);

            int mid = tL + ((tR - tL) >> 1);
            int leftIndex = (index << 1) + 1;
            if (l <= mid) {
                update(leftIndex, tL, mid, l, r, v);
            }
            if (r > mid) {
                update(leftIndex + 1, mid + 1, tR, l, r, v);
            }
            treeArr[index] = Math.max(treeArr[leftIndex], treeArr[leftIndex + 1]);
        }

        private int maxHeight(int index, int tL, int tR, int l, int r) {
            if (l <= tL && tR <= r) {
                return treeArr[index];
            }
            pushDown(index, tL, tR);

            int mid = tL + ((tR - tL) >> 1);
            int leftIndex = (index << 1) + 1;
            int retHeight = 0;
            if (l <= mid) {
                retHeight = Math.max(retHeight, maxHeight(leftIndex, tL, mid, l, r));
            }
            if (r > mid) {
                retHeight = Math.max(retHeight, maxHeight(leftIndex + 1, mid + 1, tR, l, r));
            }
            return retHeight;
        }

        private void pushDown(int index, int l, int r) {
            if (l == r || update[index] <= 0) {
                return;
            }
            int leftIndex = (index << 1) + 1;

            update[leftIndex] = update[index];
            update[leftIndex + 1] = update[index];
            treeArr[leftIndex] = update[index];
            treeArr[leftIndex + 1] = update[index];
            update[index] = 0;
        }

        /**
         * 检查下标范围是否正确
         *
         * @param l
         * @param r
         */
        private void checkRangeIndex(int l, int r) {
            if (l < 0 || r >= size || l > r) {
                throw new RuntimeException("下标范围错误");
            }
        }

    }
}
