package day20230106;

/**
 * 2180. 统计各位数字之和为偶数的整数个数
 * @author hlam
 * @date 2023/1/6
 */
public class Solution {
    /**
     * 给你一个正整数 num ，请你统计并返回 小于或等于 num 且各位数字之和为 偶数 的正整数的数目。
     * 正整数的 各位数字之和 是其所有位上的对应数字相加的结果。
     *
     * 输入：num = 30
     * 输出：14
     * 解释：
     * 只有 14 个整数满足小于等于 30 且各位数字之和为偶数，分别是：
     * 2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
     */
    public static void main(String[] args) {
        System.out.println(countEven(124));
    }
    public static int countEven(int num) {
        int ans = num / 10 * 5 - 1;
        int s = 0;
        for (int i = num / 10; i > 0; i /= 10) {
            s += i % 10;
        }
        ans += (num % 10 + 2 - (s & 1)) >> 1;
        return ans;
    }

}
