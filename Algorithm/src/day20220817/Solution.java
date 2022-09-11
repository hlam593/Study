package day20220817;

import java.util.*;

/**
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和
 * root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 15
 *
 * @author hlam
 * @date 2022/8/17
 */
public class Solution {

    public static void main(String[] args) {
        Integer[] root = {1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8};
        TreeNode treeNode = createBinTree(root, 0);
        System.out.println(treeNode);
    }

    public static TreeNode createBinTree(Integer array[], int num) {
        //根节点为第一个数
        if (array[num] == null) return null;
        TreeNode root = new TreeNode(array[num]);
        // 左孩子
        if(num * 2 + 1 < array.length){
            root.left = createBinTree(array, num * 2 + 1);
        }

        // 右孩子
        if(num * 2 + 2 < array.length){
            root.right = createBinTree(array, num * 2 + 2);
        }

        return root;
    }

    public static int deepestLeavesSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        int index = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                TreeNode node = deque.pollFirst();
                map.put(index, map.getOrDefault(index, 0) + node.val);
                if (node.left != null) deque.addLast(node.left);
                if (node.right != null) deque.addLast(node.right);
            }
            index++;
        }
        return map.get(index - 1);
    }

}
