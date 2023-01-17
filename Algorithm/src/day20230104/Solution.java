package day20230104;

/**
 * 1802. 有界数组中指定下标处的最大值
 * @author hlam
 * @date 2023/1/4
 */
public class Solution {

    /**
     * 给你三个正整数 n、index 和 maxSum 。你需要构造一个同时满足下述所有条件的数组 nums（下标 从 0 开始 计数）：
     *
     * nums.length == n
     * nums[i] 是 正整数 ，其中 0 <= i < n
     * abs(nums[i] - nums[i+1]) <= 1 ，其中 0 <= i < n-1
     * nums 中所有元素之和不超过 maxSum
     * nums[index] 的值被 最大化
     * 返回你所构造的数组中的 nums[index] 。
     *
     * 注意：abs(x) 等于 x 的前提是 x >= 0 ；否则，abs(x) 等于 -x 。
     *
     * 输入：n = 4, index = 2,  maxSum = 6
     * 输出：2
     * 解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
     */
    public static void main(String[] args) {
        System.out.println(maxValue(4, 2, 6));
    }

    public static int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (sum(mid - 1, index) + sum(mid, n - index) <= maxSum) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static long sum(long x, int cnt) {
        return x >= cnt ? (x + x - cnt + 1) * cnt / 2 : (x + 1) * x / 2 + cnt - x;
    }

}
