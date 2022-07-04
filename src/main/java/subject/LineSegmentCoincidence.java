package subject;

import java.util.*;

/**
 * 线段重合问题
 *
 * @Auther 王晓丹
 * @Date 2022/6/30 上午11:43
 */
public class LineSegmentCoincidence {

    public static void main(String[] args) {
        int[][] lineArr = new int[][]{{1, 3}, {8, 12}, {7, 15}, {2, 9}};
        int i = maxCoverLine(lineArr);
        System.out.println(i);

        int[][] rectangleArr = new int[][]{{0, 5, 3, 2}, {2, 3, 4, 0}, {2, 7, 5, 4}, {6, 4, 8, 1}};
        int maxCoverRectangleNum = maxCoverRectangleNum(rectangleArr);
        System.out.println(maxCoverRectangleNum);
    }

    /**
     * 求最大线段重合数量
     *
     * @param lineArr
     * @return
     */
    public static int maxCoverLine(int[][] lineArr) {
        if (lineArr == null || lineArr.length == 0) {
            return 0;
        }
        Arrays.sort(lineArr, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int ans = 0;
        for (int i = 0; i < lineArr.length; i++) {
            int start = lineArr[i][0];
            while (!queue.isEmpty() && queue.peek() < start) {
                queue.poll();
            }
            queue.add(lineArr[i][1]);
            ans = Math.max(ans, queue.size());
        }

        return ans;
    }

    /**
     * 求最大覆盖正方形的数量
     * // TODO 错误，需要修正，需要一个覆盖数量最多的线段算法
     *
     * @param arrs
     * @return
     */
    public static int maxCoverRectangleNum(int[][] arrs) {
        if (arrs == null || arrs.length == 0) {
            return 0;
        }
        int ans = 0;

        Rectangle[] rectangleArr = convert2Rectangle(arrs);
        Arrays.sort(rectangleArr, new RectangleDownYCompator());

        PriorityQueue<Rectangle> priorityQueue = new PriorityQueue<>(new RectangleTopYCompator());
        Set<Rectangle> set = new HashSet<>(); // set中的数据都在优先队列中存在

        for (int i = 0, j = 0; i < rectangleArr.length; i++) {
            Rectangle rectangle = rectangleArr[i];
            while (!priorityQueue.isEmpty() && priorityQueue.peek().topY < rectangle.downY) {
                Rectangle poll = priorityQueue.poll();
                set.remove(poll);
            }
            for (; j < rectangleArr.length; j++) {
                if (rectangleArr[j].downY <= rectangle.topY) {
                    priorityQueue.add(rectangleArr[j]);
                    set.add(rectangleArr[j]);
                } else {
                    break;
                }
            }
            int[][] lines = convert2Line(set);
            ans = Math.max(ans, maxCoverLine(lines));


        }

        return ans;
    }


    private static int[][] convert2Line(Set<Rectangle> set) {
        if (set == null || set.size() == 0) {
            return null;
        }
        int[][] lines = new int[set.size()][2];
        int lineIndex = 0;
        for (Rectangle rectangle : set) {
            lines[lineIndex][0] = rectangle.topX;
            lines[lineIndex][1] = rectangle.downX;
            lineIndex++;
        }
        return lines;

    }


    private static class RectangleDownYCompator implements Comparator<Rectangle> {

        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return Integer.compare(o1.downY, o2.downY);
        }
    }

    private static class RectangleTopYCompator implements Comparator<Rectangle> {

        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return Integer.compare(o1.topY, o2.topY);
        }
    }

    private static Rectangle[] convert2Rectangle(int[][] arrs) {
        Rectangle[] retArr = new Rectangle[arrs.length];
        for (int i = 0; i < arrs.length; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.topX = arrs[i][0];
            rectangle.topY = arrs[i][1];
            rectangle.downX = arrs[i][2];
            rectangle.downY = arrs[i][3];
            retArr[i] = rectangle;
        }
        return retArr;
    }


    private static class Rectangle {
        protected int topX;
        protected int topY;
        protected int downX;
        protected int downY;
    }
}
