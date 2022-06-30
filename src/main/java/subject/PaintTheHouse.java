package subject;

import java.util.Arrays;

/**
 * 粉刷房子，在一个区间内粉刷同一颜色的房子，求粉刷完以后某一个区间的颜色数量
 *
 * @Auther 王晓丹
 * @Date 2022/6/30 上午9:00
 */
public class PaintTheHouse {

    public static void main(String[] args) {
        int houseNum = 17;
        int[][] housePaintColor = new int[][]{{0, 16, 1 << 1}, {5, 8, 1 << 12}, {5, 8, 1 << 5}};

        int[] colorNumArr = getHouseColorNum(houseNum, housePaintColor);
        System.out.println(Arrays.toString(colorNumArr));


    }

    /**
     * 求每次喷完后的颜色总数量
     *
     * @param housePaintColor [N][3]
     * @return
     */
    public static int[] getHouseColorNum(int houseNum, int[][] housePaintColor) {
        if (housePaintColor == null) {
            return null;
        }
        SegmentHouse segmentHouse = new SegmentHouse(houseNum);

        int[] retArr = new int[housePaintColor.length];
        for (int i = 0; i < housePaintColor.length; i++) {
            int l = housePaintColor[i][0];
            int r = housePaintColor[i][1];
            int color = housePaintColor[i][2];
            if (getBitOneNum(color) != 1) {
                retArr[i] = -1;
                continue;
            }
            segmentHouse.paint(l, r, color);
            int colors = segmentHouse.getColors(0, houseNum - 1);
            retArr[i] = getBitOneNum(colors);
        }

        return retArr;
    }

    /**
     * 线段树改写
     */
    private static class SegmentHouse {
        private int size;
        private int[] tree;
        private int[] update;

        /**
         * 传入房子数量
         *
         * @param houseSize
         */
        public SegmentHouse(int houseSize) {
            this.size = houseSize;
            int treeSize = (houseSize << 1) + 1;
            tree = new int[treeSize];
            update = new int[treeSize];
        }

        /**
         * 在区间范围内粉刷房子
         *
         * @param l
         * @param r
         * @param color
         */
        public void paint(int l, int r, int color) {
            checkIndexRang(l, r);
            paint(0, 0, size - 1, l, r, color);
        }

        public int getColors(int l, int r) {
            checkIndexRang(l, r);
            return getColors(0, 0, size - 1, l, r);
        }


        private int getColors(int tIndex, int tL, int tR, int l, int r) {
            if (l <= tL && tR <= r) {
                return tree[tIndex];
            }
            pushDown(tIndex, tL, tR);
            int leftIndex = getLeftIndex(tIndex);
            int mid = getMid(tL, tR);
            int leftColors = 0;
            int rightColors = 0;
            if (l <= mid) {
                leftColors = getColors(leftIndex, tL, mid, l, r);
            }
            if (r > mid) {
                rightColors = getColors(leftIndex + 1, mid + 1, tR, l, r);
            }
            return leftColors | rightColors;
        }

        private void paint(int tIndex, int tL, int tR, int l, int r, int color) {
            if (l <= tL && tR <= r) {
                tree[tIndex] = color;
                update[tIndex] = color;
                return;
            }
            pushDown(tIndex, tL, tR);

            int leftIndex = getLeftIndex(tIndex);
            int mid = getMid(tL, tR);
            if (l <= mid) {
                paint(leftIndex, tL, mid, l, r, color);
            }
            if (r > mid) {
                paint(leftIndex + 1, mid + 1, tR, l, r, color);
            }
            pushUp(tIndex);
        }


        private void pushDown(int index, int l, int r) {
            if (l == r || update[index] == 0) {
                return;
            }
            int leftIndex = getLeftIndex(index);
            tree[leftIndex] = update[index];
            update[leftIndex] = update[index];
            tree[leftIndex + 1] = update[index];
            update[leftIndex + 1] = update[index];
            update[index] = 0;
        }


        private void pushUp(int index) {
            int leftIndex = getLeftIndex(index);
            tree[index] = tree[leftIndex] | tree[leftIndex + 1];
        }

        private int getLeftIndex(int index) {
            return (index << 1) + 1;
        }

        private int getMid(int l, int r) {
            return l + ((r - l) >> 1);
        }

        private void checkIndexRang(int l, int r) {
            if (l < 0 || r >= size || l > r) {
                throw new RuntimeException("区间下标位置错误");
            }
        }
    }


    /**
     * Integer.bitCount
     *
     * @param num
     * @return
     */
    private static int getBitOneNum(int num) {
        int ans = 0;
        for (int i = 1; num != 0; i <<= 1) {
            if ((num & i) != 0) {
                ans++;
                num = num & (~i);
            }
        }
        return ans;
    }

}
