package base;

/**
 * 二叉搜索树
 *
 * @Auther 王晓丹
 * @Date 2022/7/4 上午9:56
 */
public class BinarySearchTree {
    private Node root;
    private int size;


    /**
     * 中序遍历
     * morris遍历
     */
    public void midOrder() {
        Node cur = root;
        while (cur != null) {
            Node mostRightLeft = cur.left;
            if (mostRightLeft != null) {
                while (mostRightLeft.right != null && mostRightLeft.right != cur) {
                    mostRightLeft = mostRightLeft.right;
                }

                if (mostRightLeft.right == null) {
                    mostRightLeft.right = cur;
                    cur = cur.left;
                    continue;
                }
                mostRightLeft.right = null;
            }
            System.out.print(cur.key + "\t");
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * 删除节点位置
     * // 可能会换头，返回被删除节点值
     *
     * @param key
     * @return
     */
    public Object delete(int key) {
        Node node = searchNode(key);
        if (node == null) {
            return null;
        }
        root = delete(node);
        return node.value;
    }

    /**
     * 删除给定节点，并返回新的头节点
     *
     * @param delNode
     * @return
     */
    private Node delete(Node delNode) {
        Node replaceNode = null;
        if (delNode.left == null && delNode.right == null) {
            // 无左无右孩子节点
            replaceParentChild(delNode, null);
        } else if (delNode.left == null || delNode.right == null) {
            // 左孩子为空或者右孩子为空
            replaceNode = delNode.left != null ? delNode.left : delNode.right;
            replaceParentChild(delNode, replaceNode);
        } else {
            // 左右孩子都不为空
            replaceNode = delNode.right;
            while (replaceNode.left != null) {
                // 查找后继结点
                replaceNode = replaceNode.left;
            }
            replaceParentChild(replaceNode, replaceNode.right);
            replaceParentChild(delNode, replaceNode);
            replaceNode.left = delNode.left;
            replaceNode.right = delNode.right;
        }
        delNode.parent = null;
        delNode.left = null;
        delNode.right = null;
        return delNode == root ? replaceNode : root;
    }

    private void replaceParentChild(Node oldChild, Node newChild) {
        if (newChild != null) {
            newChild.parent = oldChild.parent;
        }
        if (oldChild.parent == null) {
            return;
        }
        Node parent = oldChild.parent;
        if (parent.left == oldChild) {
            parent.left = newChild;
        } else {
            parent.right = newChild;
        }
    }

    public Object search(int key) {
        Node node = searchNode(key);
        return node != null ? node.value : null;
    }


    private Node searchNode(int key) {
        Node cur = root;
        while (cur != null && cur.key != key) {
            cur = cur.key > key ? cur.left : cur.right;
        }
        return cur;
    }

    public boolean insert(int key, Object value) {
        Node node = new Node(key, value);
        if (root == null) {
            root = node;
            size++;
            return true;
        }
        Node cur = root;
        while (true) {
            if (cur.key == key) {
                // 已经存在，无法继续插入
                return false;
            }
            Node next = cur.key > key ? cur.left : cur.right;
            if (next != null) {
                cur = next;
                continue;
            }
            next = new Node(key, value);
            if (cur.key > key) {
                cur.left = next;
            } else {
                cur.right = next;
            }
            next.parent = cur;
            size++;
            break;
        }
        return true;
    }

    /**
     * 树节点
     */
    private static class Node {
        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        private int key;
        private Object value;
        private Node parent;
        private Node left;
        private Node right;

    }


    public static void main(String[] args) {
        int[] intArr = new int[]{4, 3, 8, 1, 2, 7, 9, 5, 10};
        BinarySearchTree tree = new BinarySearchTree();
        for (int num : intArr) {
            tree.insert(num, num + "_" + ((char) ('a' + num - 1)));
        }
        tree.midOrder();

        Object value = tree.search(10);
        System.out.println(value);

        Object value02 = tree.delete(3);
        System.out.println(value02);
        Object value03 = tree.delete(8);
        System.out.println(value03);
        tree.midOrder();
    }


}
