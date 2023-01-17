package day20230117;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 1814. 统计一个数组中好对子的数目
 *
 * @author hlam
 * @date 2023/1/17
 */
public class Solution {
    /**
     * 给你一个数组nums，数组中只包含非负整数。定义rev(x)的值为将整数x各个数字位反转得到的结果。
     * 比方说rev(123) = 321，rev(120) = 21。我们称满足下面条件的下标对(i, j) 是好的：
     * <p>
     * 0 <= i < j < nums.length
     * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
     * 请你返回好下标对的数目。由于结果可能会很大，请将结果对109 + 7取余后返回。
     * <p>
     * 输入：nums = [42,11,1,97]
     * 输出：2
     * 解释：两个坐标对为：
     * - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
     * - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
     * <p>
     * 输入：nums = [13,10,35,24,76]
     * 输出：4
     */
    @Test
    public void test() {
        System.out.println(countNicePairs(new int[]{42, 11, 1, 97}));
    }

    public int countNicePairs(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            int y = x - rev(x);
            cnt.merge(y, 1, Integer::sum);
        }
        final int mod = (int) (1e9 + 7);
        long ans = 0;
        for (Integer value : cnt.values()) {
            ans = (ans + (long) value * (value - 1) / 2) % mod;
        }
        return (int) ans;
    }

    private int rev(int x) {
        int y = 0;
        for (; x > 0; x /= 10) {
            y = y * 10 + x % 10;
        }
        return y;
    }
}
