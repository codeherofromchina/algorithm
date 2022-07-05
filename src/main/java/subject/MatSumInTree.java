package subject;

/**
 * 给定一个二叉树，求最大路径和
 *
 * @Auther 王晓丹
 * @Date 2022/7/5 下午12:16
 */
public class MatSumInTree {

    public static void main(String[] args) {
        Node head = new Node(-2);
        head.left = new Node(-3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right = new Node(-2);

        int pathSum01 = maxPath01(head);
        System.out.println(pathSum01);
        int pathSum02 = maxPath02(head);
        System.out.println(pathSum02);
        int pathSum03 = maxPath03(head);
        System.out.println(pathSum03);
    }

    /**
     * 路径必须以头节点出发，到叶子节点为止，返回最大路径和
     *
     * @param head
     * @return
     */
    public static int maxPath01(Node head) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null) {
            return head.value;
        }
        int leftPathSum = Integer.MIN_VALUE;
        int rightPathSum = Integer.MIN_VALUE;
        if (head.left != null) {
            leftPathSum = maxPath01(head.left);
        }
        if (head.right != null) {
            rightPathSum = maxPath01(head.right);
        }

        return head.value + Math.max(leftPathSum, rightPathSum);
    }

    /**
     * 路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
     *
     * @param head
     * @return
     */
    public static int maxPath02(Node head) {
        if (head == null) {
            return 0;
        }
        Info sumStru = process02(head);
        return sumStru.getMax();
    }

    private static Info process02(Node node) {
        if (node.right == null && node.left == null) {
            return new Info(node.value, Integer.MIN_VALUE);
        }
        int head = 0;
        int noHead = Integer.MIN_VALUE;
        if (node.right != null) {
            Info rightStru = process02(node.right);
            head = Math.max(head, rightStru.fromHeadMaxSum);
            noHead = rightStru.getMax();
        }

        if (node.left != null) {
            Info leftStru = process02(node.left);
            head = Math.max(head, leftStru.fromHeadMaxSum);
            noHead = Math.max(noHead, leftStru.getMax());
        }

        return new Info(head + node.value, noHead);
    }


    /**
     * 路径可以从任何节点出发，到任何节点，返回最大路径和
     *
     * @param head
     * @return
     */
    public static int maxPath03(Node head) {
        if (head == null) {
            return 0;
        }
        Info sumStru = process03(head);
        return sumStru.getMax();
    }

    private static Info process03(Node node) {
        if (node.left == null && node.right == null) {
            return new Info(node.value, node.value);
        }

        int head = 0;
        int noHead = Integer.MIN_VALUE;
        int noHead02 = node.value;
        if (node.left != null) {
            Info leftStru = process03(node.left);
            head = Math.max(head, leftStru.fromHeadMaxSum);
            noHead = Math.max(noHead, leftStru.allTreeMaxSum);
            if (leftStru.fromHeadMaxSum > 0) {
                noHead02 += leftStru.fromHeadMaxSum;
            }
        }

        if (node.right != null) {
            Info rightStru = process03(node.right);
            head = Math.max(head, rightStru.fromHeadMaxSum);
            noHead = Math.max(noHead, rightStru.allTreeMaxSum);
            if (rightStru.fromHeadMaxSum > 0) {
                noHead02 += rightStru.fromHeadMaxSum;
            }
        }


        return new Info(head + node.value, Math.max(noHead, noHead02));
    }

    private static class Info {
        public Info(int head, int noHead) {
            this.fromHeadMaxSum = head;
            this.allTreeMaxSum = noHead;
        }

        int fromHeadMaxSum;
        int allTreeMaxSum;

        int getMax() {
            return Math.max(fromHeadMaxSum, allTreeMaxSum);
        }
    }


    private static class Node {
        public Node(int value) {
            this.value = value;
        }

        private int value;
        private Node left;
        private Node right;
    }
}
