package day20230105;

/**
 * 1803. 统计异或值在范围内的数对有多少
 * @author hlam
 * @date 2023/1/5
 */
public class Solution {
    /**
     * 给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。
     *
     * 漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。
     * 输入：nums = [1,4,2,7], low = 2, high = 6
     * 输出：6
     * 解释：所有漂亮数对 (i, j) 列出如下：
     *     - (0, 1): nums[0] XOR nums[1] = 5
     *     - (0, 2): nums[0] XOR nums[2] = 3
     *     - (0, 3): nums[0] XOR nums[3] = 6
     *     - (1, 2): nums[1] XOR nums[2] = 6
     *     - (1, 3): nums[1] XOR nums[3] = 3
     *     - (2, 3): nums[2] XOR nums[3] = 5
     *
     * 输入：nums = [9,8,4,2,1], low = 5, high = 14
     * 输出：8
     * 解释：所有漂亮数对 (i, j) 列出如下：
     *    - (0, 2): nums[0] XOR nums[2] = 13
     *    - (0, 3): nums[0] XOR nums[3] = 11
     *    - (0, 4): nums[0] XOR nums[4] = 8
     *    - (1, 2): nums[1] XOR nums[2] = 12
     *    - (1, 3): nums[1] XOR nums[3] = 10
     *    - (1, 4): nums[1] XOR nums[4] = 9
     *    - (2, 3): nums[2] XOR nums[3] = 6
     *    - (2, 4): nums[2] XOR nums[4] = 5
     */

    public static void main(String[] args) {
        System.out.println(countPairs(new int[]{9,8,4,2,1}, 5, 14));
    }

    public static int countPairs(int[] nums, int low, int high) {
        Tree tree = new Tree();
        int ans = 0;
        for (int num : nums) {
            ans += tree.search(num, high + 1) - tree.search(num, low);
            tree.insert(num);
        }
        return ans;
    }

}

class Tree {
    private Tree[] children = new Tree[2];
    private int cnt;

    public void insert(int x) {
        Tree node = this;
        for (int i = 15; i >= 0; i --) {
            int v = (x >> i) & 1;
            if (node.children[v] == null) {
                node.children[v] = new Tree();
            }
            node = node.children[v];
            ++ node.cnt;
        }
    }

    public int search(int x, int limit) {
        Tree node = this;
        int ans = 0;
        for (int i = 15; i >= 0 && node != null; i --) {
            int v = (x >> i) & 1;
            if (((limit >> i) & 1) == 1) {
                if (node.children[v] != null) {
                    ans += node.children[v].cnt;
                }
                node = node.children[v ^ 1];
            } else {
                node = node.children[v];
            }
        }
        return ans;
    }
}
