package base;

import structure.TreeNode;

/**
 * morris遍历树
 *
 * @Auther 王晓丹
 * @Date 2022/6/29 上午10:13
 */
public class Morris {


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
        morris(head);
        System.out.println("\n递归前序遍历：");
        preorderTraversal(head);
        System.out.println("\nmorris前序遍历：");
        preorderTraversalByMorris(head);
        System.out.println("\n递归中序遍历：");
        inorderTraversal(head);
        System.out.println("\nmorris中序遍历：");
        inorderTraversalByMorris(head);
        System.out.println("\n递归后序遍历：");
        postorderTraversal(head);
        System.out.println("\r\nmorris后序遍历：");
        postorderTraversalByMorris(head);


        TreeNode head02 = new TreeNode("1");
        head02.left = new TreeNode("2");
        head02.left.right = new TreeNode("3");
        head02.left.right.left = new TreeNode("10");
        head02.left.right.left.left = new TreeNode("11");
        head02.left.right.left.right = new TreeNode("12");
        head02.right = new TreeNode("4");
        head02.right.right = new TreeNode("5");
        head02.right.right.left = new TreeNode("6");
        head02.right.right.left.left = new TreeNode("8");
        head02.right.right.left.right = new TreeNode("9");
        head02.right.right.right = new TreeNode("7");

        System.out.println("\r\n递归求最小高度");
        System.out.println(minHeight(head02));
        System.out.println("morris求最小高度");
        System.out.println(minHeightByMorris(head02));
    }


    private static void preorderTraversal(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + "\t");
        preorderTraversal(head.left);
        preorderTraversal(head.right);
    }

    private static void preorderTraversalByMorris(TreeNode head) {
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


    public static void morris(TreeNode head) {
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
     * morris方法
     */
    public static void inorderTraversalByMorris(TreeNode head) {
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
     * morris方法
     *
     * @param head
     */
    public static void postorderTraversalByMorris(TreeNode head) {
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

    public static int minHeight(TreeNode head) {
        if (head == null) {
            return 0;
        }
        if (head.left == null && head.right == null) {
            return 1;
        }

        int answer = Integer.MAX_VALUE;
        if (head.left != null) {
            answer = minHeight(head.left);
        }
        if (head.right != null) {
            answer = Math.min(answer, minHeight(head.right));
        }

        return answer + 1;
    }


    /**
     * 求一个数的叶子结点最小高度
     *
     * @return
     */
    public static int minHeightByMorris(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int answer = Integer.MAX_VALUE;
        int height = 1;
        TreeNode cur = head;
        TreeNode pre = null;
        TreeNode leftMostRight;
        while (cur != null) {
            leftMostRight = cur.left;
            if (leftMostRight != null) {
                int leftHeight = 1;
                while (leftMostRight.right != null && leftMostRight.right != cur) {
                    leftMostRight = leftMostRight.right;
                    leftHeight++;
                }
                if (leftMostRight.right == null) {
                    if (leftMostRight.left == null) {
                        // 左树最右节点是叶子节点，收集答案
                        answer = Math.min(answer, height + leftHeight);
                    }
                    leftMostRight.right = cur;
                    cur = cur.left;
                    height++;
                    continue;
                }
                leftMostRight.right = null;
                if (pre == leftMostRight) {
                    height -= (leftHeight + 1);
                }
            }
            pre = cur;
            cur = cur.right;
            height++;
        }
        // 最右侧是否是叶子节点
        cur = head;
        height = 1;
        while (cur.right != null) {
            height++;
            cur = cur.right;
        }
        if (cur.left == null) {
            answer = Math.min(answer, height);
        }

        return answer;
    }
}

