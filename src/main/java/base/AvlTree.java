package base;

/**
 * 平衡二叉树
 * TODO  未完成，SizeBalanceTree和红黑树和跳表都未完成
 * 有时间继续研究
 *
 * @Auther 王晓丹
 * @Date 2022/7/4 下午3:16
 */
public class AvlTree {
    private AVLNode<Object> root;

    public AVLNode delete(int key) {
        if (root == null) {
            return null;
        }
        root = delete(root, key);
        return null;
    }


    private AVLNode delete(AVLNode root, int key) {

        return null;
    }


    public AVLNode search(int key) {
        AVLNode cur = root;
        while (cur != null && cur.key != key) {
            cur = cur.key > key ? cur.left : cur.right;
        }
        return cur;
    }

    /**
     * 插入节点
     * 不可插入重复key节点
     *
     * @param key
     */
    public void insert(int key) {
        if (root == null) {
            root = new AVLNode<>(key, null, new Object());
            return;
        }
        this.root = insert(root, key);
    }

    public AVLNode insert(AVLNode root, int key) {
        if (root.key > key) {
            if (root.left == null) {
                root.left = new AVLNode(key, root, new Object());
            } else {
                insert(root.left, key);
            }
        }
        if (root.key < key) {
            if (root.right == null) {
                root.right = new AVLNode(key, root, new Object());
            } else {
                insert(root.right, key);
            }
        }
        calcHeight(root);
        if (calcBF(root) == 2) {
            if (calcBF(root.left) == -1) {
                root.left = leftRotate(root.left);
            }
            root = rightRotate(root);
        }

        if (calcBF(root) == -2) {
            if (calcBF(root.right) == 1) {
                root.right = rightRotate(root.right);
            }
            root = leftRotate(root);
        }
        return root;
    }


    private int calcBF(AVLNode node) {
        int leftHeight = node.left != null ? node.left.height : 0;
        int rightHeight = node.right != null ? node.right.height : 0;
        return leftHeight - rightHeight;
    }

    /**
     * 右旋
     *
     * @param node
     * @return
     */
    private AVLNode rightRotate(AVLNode node) {
        AVLNode newNode = node.left;
        AVLNode parent = node.parent;

        if (parent != null) {
            if (parent.left == node) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
        newNode.parent = parent;

        node.left = newNode.right;
        newNode.right = node;
        calcHeight(node);
        calcHeight(newNode);

        return newNode;
    }


    private AVLNode leftRotate(AVLNode node) {
        AVLNode newNode = node.right;
        AVLNode parent = node.parent;

        if (parent != null) {
            if (parent.left == node) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
        newNode.parent = parent;

        node.right = newNode.left;
        newNode.left = node;

        calcHeight(node);
        calcHeight(newNode);
        return newNode;
    }


    private void calcHeight(AVLNode node) {
        int leftHeight = node.left == null ? 0 : node.left.height;
        int rightHeight = node.right == null ? 0 : node.right.height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    private static class AVLNode<E> {
        public AVLNode(int key, AVLNode parent, E value) {
            this.key = key;
            this.parent = parent;
            this.value = value;
            this.height = 1;
        }

        private int key;
        private E value;
        private int height;
        private AVLNode parent;
        private AVLNode left;
        private AVLNode right;
    }


    public static void main(String[] args) {


    }
}
