package base;

import java.util.*;

/**
 * AC自动机
 *
 * @Auther 王晓丹
 * @Date 2022/7/3 下午6:02
 */
public class AcAutomata {
    private Node root;

    public AcAutomata() {
        root = new Node();
    }

    /**
     * 返回文章中所有包含的单词
     *
     * @param article
     * @return
     */
    public List<String> containWords(String article) {
        List<String> retList = new ArrayList<>();
        if (article == null || article.length() == 0) {
            return retList;
        }

        char[] str = article.toCharArray();
        Node cur = root;
        Node follow = null;
        for (char c : str) {
            while (!cur.next.containsKey(c) && cur != root) {
                cur = cur.fail;
            }

            cur = cur.next.getOrDefault(c, root);
            follow = cur;
            while (follow != root) {
                if (follow.endUsed) {
                    break;
                }
                if (follow.end != null) {
                    // 收集答案
                    retList.add(follow.end);
                    follow.endUsed = true;
                }
                follow = follow.fail;
            }
        }
        return retList;
    }

    /**
     * 构建自动机fail指针
     */
    public void build() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Character c : cur.next.keySet()) {
                Node node = cur.next.get(c);
                node.fail = root;
                Node fNode = cur;
                while (fNode != root) {
                    if (fNode.fail.next.containsKey(c)) {
                        node.fail = fNode.fail.next.get(c);
                        break;
                    }
                    fNode = fNode.fail;
                }
                queue.add(node);
            }
        }
    }


    /**
     * 插入单词，构建前缀树
     *
     * @param word
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        char[] str = word.toCharArray();
        Node cur = root;
        for (char c : str) {
            if (cur.next.containsKey(c)) {
                cur = cur.next.get(c);
                continue;
            }
            Node node = new Node();
            cur.next.put(c, node);
            cur = node;
        }
        cur.end = word;
    }

    private static class Node {
        private String end;
        private boolean endUsed;
        private Node fail;
        private Map<Character, Node> next = new HashMap<>();
    }


    public static void main(String[] args) {
        AcAutomata acAutomata = new AcAutomata();
        acAutomata.insert("abcdef");
        acAutomata.insert("bcdef");
        acAutomata.insert("def");
        acAutomata.insert("ef");
        acAutomata.build();

        List<String> stringList = acAutomata.containWords("abcdef");
        for (String s : stringList) {

            System.out.println(s);
        }


    }
}
