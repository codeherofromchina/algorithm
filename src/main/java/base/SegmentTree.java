package base;

import java.util.Arrays;

/**
 * SegmentTree 线段树
 *
 * @Auther 王晓丹
 * @Date 2022/6/29 下午3:43
 */
public class SegmentTree {
    private boolean inited;
    // 原始数组
    private int[] arr;
    private int size;
    // 树结构数组
    private int[] treeArr;
    // 延迟增加数组
    private int[] lazyAddArr;
    // 延迟更新数组
    private int[] lazyUpdateArr;
    // 是否存在延迟更新
    private boolean[] updateFlagArr;

    public SegmentTree(int[] arr) {
        this.arr = arr;
        this.size = arr.length;
    }

    public void init() {
        int treeSize = (size << 1) + 1;
        treeArr = new int[treeSize];
        lazyAddArr = new int[treeSize];
        lazyUpdateArr = new int[treeSize];
        updateFlagArr = new boolean[treeSize];
        initTreeArr(0, 0, size - 1);
        inited = true;
    }


    /**
     * 初始化treeArr中的值
     *
     * @param index
     * @param l
     * @param r
     */
    private void initTreeArr(int index, int l, int r) {
        if (l == r) {
            treeArr[index] = arr[l];
            return;
        }
        int leftIndex = (index << 1) + 1;
        int mid = l + ((r - l) >> 1);
        // 初始化左树
        initTreeArr(leftIndex, l, mid);
        // 初始化右树
        initTreeArr(leftIndex + 1, mid + 1, r);

        treeArr[index] = treeArr[leftIndex] + treeArr[leftIndex + 1];
    }

    /**
     * 在区间L ~ R 上统一增加值V
     *
     * @param l
     * @param r
     * @param v
     */
    public void add(int l, int r, int v) {
        isInit();
        checkRange(l, r);
        add(0, 0, size - 1, l, r, v);
    }

    private void add(int tIndex, int tL, int tR, int l, int r, int v) {
        if (l <= tL && tR <= r) {
            treeArr[tIndex] += ((tR - tL + 1) * v);
            lazyAddArr[tIndex] += v;
            return;
        }
        lazyDown(tIndex, tL, tR);
        int leftIndex = (tIndex << 1) + 1;
        int mid = tL + ((tR - tL) >> 1);
        if (l <= mid) {
            add(leftIndex, tL, mid, l, r, v);
        }
        if (r > mid) {
            add(leftIndex + 1, mid + 1, tR, l, r, v);
        }
        treeArr[tIndex] = treeArr[leftIndex] + treeArr[leftIndex + 1];
    }

    /**
     * 在区间L ~ R 上统一修改为值V
     *
     * @param l
     * @param r
     * @param v
     */
    public void update(int l, int r, int v) {
        isInit();
        checkRange(l, r);
        update(0, 0, size - 1, l, r, v);
    }

    private void update(int tIndex, int tL, int tR, int l, int r, int v) {
        if (l <= tL && tR <= r) {
            treeArr[tIndex] = ((tR - tL + 1) * v);
            lazyAddArr[tIndex] = 0;
            lazyUpdateArr[tIndex] = v;
            updateFlagArr[tIndex] = true;
            return;
        }
        lazyDown(tIndex, tL, tR);
        int leftIndex = (tIndex << 1) + 1;
        int mid = tL + ((tR - tL) >> 1);
        if (l <= mid) {
            update(leftIndex, tL, mid, l, r, v);
        }
        if (r > mid) {
            update(leftIndex + 1, mid + 1, tR, l, r, v);
        }
        treeArr[tIndex] = treeArr[leftIndex] + treeArr[leftIndex + 1];
    }

    /**
     * 获取L ~ R 区间上的累加和
     *
     * @param l
     * @param r
     * @return
     */
    public int getSum(int l, int r) {
        isInit();
        checkRange(l, r);
        return getValue(0, 0, size - 1, l, r);
    }


    private int getValue(int tIndex, int tL, int tR, int l, int r) {
        if (l <= tL && r >= tR) {
            return treeArr[tIndex];
        }
        lazyDown(tIndex, tL, tR);
        int leftIndex = (tIndex << 1) + 1;
        int mid = tL + ((tR - tL) >> 1);
        int result = 0;
        if (l <= mid) {
            result += getValue(leftIndex, tL, mid, l, r);
        }
        if (r > mid) {
            result += getValue(leftIndex + 1, mid + 1, tR, l, r);
        }
        return result;
    }

    /**
     * 向下延迟更新
     *
     * @param index
     * @param l
     * @param r
     */
    private void lazyDown(int index, int l, int r) {
        if (l == r) {
            return;
        }
        int leftIndex = (index << 1) + 1;
        int rightIndex = leftIndex + 1;
        int mid = l + ((r - l) >> 1);
        if (updateFlagArr[index]) {
            // 存在向下懒更新
            lazyAddArr[leftIndex] = 0;
            lazyAddArr[rightIndex] = 0;
            treeArr[leftIndex] = lazyUpdateArr[index] * (mid - l + 1);
            treeArr[rightIndex] = lazyUpdateArr[index] * (r - mid);
            updateFlagArr[leftIndex] = true;
            lazyUpdateArr[leftIndex] = lazyUpdateArr[index];

            updateFlagArr[rightIndex] = true;
            lazyUpdateArr[rightIndex] = lazyUpdateArr[index];
            updateFlagArr[index] = false;
        }

        if (lazyAddArr[index] != 0) {
            // 存在向下懒相加
            treeArr[leftIndex] += lazyAddArr[index] * (mid - l + 1);
            treeArr[rightIndex] += lazyAddArr[index] * (r - mid);
            lazyAddArr[leftIndex] += lazyAddArr[index];
            lazyAddArr[rightIndex] += lazyAddArr[index];
            lazyAddArr[index] = 0;
        }
    }


    private void isInit() {
        if (!inited) {
            throw new RuntimeException("未初始化，请调用init()方法初始化");
        }
    }


    private void checkRange(int l, int r) {
        if (l < 0 || r >= size || l > r) {
            throw new RuntimeException("区间错误，请保证范围在0 ~ " + size + "内");
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.init();

        getSum(segmentTree, 0, arr.length - 1);

        segmentTree.add(0, 1, 1);

        getSum(segmentTree, 0, arr.length - 1);

        segmentTree.update(2, 3, 1);
        getSum(segmentTree, 0, arr.length - 1);
    }


    private static void getSum(SegmentTree segmentTree, int l, int r) {

        System.out.println(Arrays.toString(segmentTree.treeArr));

        int sum = segmentTree.getSum(l, r);
        System.out.println(sum);
    }
}
