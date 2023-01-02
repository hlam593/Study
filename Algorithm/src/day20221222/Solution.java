package day20221222;

/**
 * 1799. N 次操作后的最大分数和
 * @author hlam
 * @date 2022/12/22
 */
public class Solution {

    /**
     * 给你 nums，它是一个大小为 2 * n 的正整数数组。你必须对这个数组执行 n 次操作。
     * 在第 i 次操作时（操作编号从 1 开始），你需要：
     * 选择两个元素 x 和 y。
     * 获得分数 i * gcd(x, y)。
     * 将 x 和 y 从 nums 中删除。
     * 请你返回 n 次操作后你能获得的分数和最大为多少。
     * 函数 gcd(x, y) 是 x 和 y 的最大公约数。
     *
     * 输入：nums = [1,2]
     * 输出：1
     * 解释：最优操作是：
     * (1 * gcd(1, 2)) = 1
     *
     * 输入：nums = [3,4,6,8]
     * 输出：11
     * 解释：最优操作是：
     * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
     *
     * 输入：nums = [1,2,3,4,5,6]
     * 输出：14
     * 解释：最优操作是：
     * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
     */

    public int maxScore(int[] nums) {
        int m = nums.length;
        int[][] g = new int[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = i + 1; j < m; ++j) {
                g[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int[] f = new int[1 << m];
        for (int k = 0; k < 1 << m; ++k) {
            int cnt = Integer.bitCount(k);
            if (cnt % 2 == 0) {
                for (int i = 0; i < m; ++i) {
                    if (((k >> i) & 1) == 1) {
                        for (int j = i + 1; j < m; ++j) {
                            if (((k >> j) & 1) == 1) {
                                f[k] = Math.max(f[k], f[k ^ (1 << i) ^ (1 << j)] + cnt / 2 * g[i][j]);
                            }
                        }
                    }
                }
            }
        }
        return f[(1 << m) - 1];
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
