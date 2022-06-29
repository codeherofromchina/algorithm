package base;

import structure.TreeNode;

/**
 * mirrors遍历树
 *
 * @Auther 王晓丹
 * @Date 2022/6/29 上午10:13
 */
public class Morrors {


    public static void main(String[] args) {
        TreeNode head = new TreeNode("1");
        head.left = new TreeNode("2");
        head.right = new TreeNode("3");
        head.left.left = new TreeNode("4");
        head.left.right = new TreeNode("5");
        head.right.left = new TreeNode("6");
        head.right.right = new TreeNode("7");
        head.right.right.left = new TreeNode("8");
        head.right.right.right = new TreeNode("9");
        mirrors(head);
        System.out.println("\n递归前序遍历：");
        preorderTraversal(head);
        System.out.println("\nmirrors前序遍历：");
        preorderTraversalByMirrors(head);
        System.out.println("\n递归中序遍历：");
        inorderTraversal(head);
        System.out.println("\nmirrors中序遍历：");
        inorderTraversalByMirrors(head);
        System.out.println("\n递归后序遍历：");
        postorderTraversal(head);
        System.out.println("\r\nmirrors后序遍历：");
        postorderTraversalByMirrors(head);
        System.out.println();
    }


    private static void preorderTraversal(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + "\t");
        preorderTraversal(head.left);
        preorderTraversal(head.right);
    }

    private static void preorderTraversalByMirrors(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode leftMostRight;
        while (cur != null) {
            leftMostRight = cur.left;
            if (leftMostRight != null) {
                while (leftMostRight.right != null && leftMostRight.right != cur) {
                    leftMostRight = leftMostRight.right;
                }
                if (leftMostRight.right == null) {
                    System.out.print(cur.value + "\t");
                    leftMostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                leftMostRight.right = null;
            } else {
                System.out.print(cur.value + "\t");
            }
            cur = cur.right;
        }
    }


    public static void mirrors(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        while (cur != null) {
            System.out.print(cur.value + "\t");
            TreeNode leftMostRight = cur.left;
            if (leftMostRight != null) {
                while (leftMostRight.right != null && leftMostRight.right != cur) {
                    leftMostRight = leftMostRight.right;
                }
                if (leftMostRight.right == null) {
                    leftMostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    leftMostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }


    /**
     * 中序遍历
     * mirrors方法
     */
    public static void inorderTraversalByMirrors(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode leftMostRight = null;
        while (cur != null) {
            leftMostRight = cur.left;
            if (leftMostRight != null) {
                while (leftMostRight.right != null && leftMostRight.right != cur) {
                    leftMostRight = leftMostRight.right;
                }
                if (leftMostRight.right == null) {
                    leftMostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    leftMostRight.right = null;
                }
            }
            System.out.print(cur.value + "\t");
            cur = cur.right;
        }

    }

    /**
     * 中序遍历
     * 递归方法
     */
    public static void inorderTraversal(TreeNode head) {
        if (head == null) {
            return;
        }
        inorderTraversal(head.left);
        System.out.print(head.value + "\t");
        inorderTraversal(head.right);
    }

    /**
     * 后序遍历
     * mirrors方法
     *
     * @param head
     */
    public static void postorderTraversalByMirrors(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode leftMostRight;
        while (cur != null) {
            leftMostRight = cur.left;
            if (leftMostRight != null) {
                while (leftMostRight.right != null && leftMostRight.right != cur) {
                    leftMostRight = leftMostRight.right;
                }
                if (leftMostRight.right == null) {
                    leftMostRight.right = cur;
                    cur = cur.left;
                    continue;

                }

                leftMostRight.right = null;
                reversePrintNodeRightLine(cur.left);
            }
            cur = cur.right;
        }
        reversePrintNodeRightLine(head);
    }

    /**
     * 后续遍历
     * 递归方法
     *
     * @param head
     */
    public static void postorderTraversal(TreeNode head) {
        if (head == null) {
            return;
        }
        postorderTraversal(head.left);
        postorderTraversal(head.right);
        System.out.print(head.value + "\t");
    }

    /**
     * 逆序打印节点右侧树上的一行数据
     *
     * @param node
     */
    private static void reversePrintNodeRightLine(TreeNode node) {
        if (node == null) {
            return;
        }
        // 反转列表
        TreeNode pre = null;
        TreeNode cur = node;
        TreeNode post = null;
        while (cur != null) {
            post = cur.right;
            cur.right = pre;
            pre = cur;
            cur = post;
        }
        // 打印列表
        cur = pre;
        while (cur != null) {
            System.out.print(cur.value + "\t");
            cur = cur.right;
        }

        // 反转还原
        cur = pre;
        pre = null;
        while (cur != null) {
            post = cur.right;
            cur.right = pre;
            pre = cur;
            cur = post;
        }
    }
}

