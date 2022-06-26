package structure;

import lombok.Data;

/**
 * 二叉树树节点
 *
 * @Auther 王晓丹
 * @Date 2022/6/26 上午10:57
 */
@Data
public class TreeNode {

    public TreeNode(String value) {
        this.value = value;
    }

    /**
     * 节点数据
     */
    private String value;
    /**
     * 左树节点
     */
    private TreeNode right;
    /**
     * 右树节点
     */
    private TreeNode left;

}
