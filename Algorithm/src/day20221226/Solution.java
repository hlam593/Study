package day20221226;

/**
 * 1759. 统计同构子字符串的数目
 * @author hlam
 * @date 2022/12/26
 */
public class Solution {

    /**
     * 给你一个字符串 s ，返回 s 中 同构子字符串 的数目。由于答案可能很大，只需返回对 109 + 7 取余 后的结果。
     * 同构字符串 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同构字符串。
     * 子字符串 是字符串中的一个连续字符序列。
     *
     * 输入：s = "abbcccaa"
     * 输出：13
     * 解释：同构子字符串如下所列：
     * "a"   出现 3 次。
     * "aa"  出现 1 次。
     * "b"   出现 2 次。
     * "bb"  出现 1 次。
     * "c"   出现 3 次。
     * "cc"  出现 2 次。
     * "ccc" 出现 1 次。
     * 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13
     */

    public static void main(String[] args) {
        System.out.println(countHomogenous("abbcccaa"));
    }

    private final static int MOD = (int)1e9 + 7;

    public static int countHomogenous(String s) {
        long res = 0;
        int cnt = 0;
        char pre = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == pre) {
                cnt ++;
            } else {
                res += (long) (cnt + 1) * cnt / 2;
                cnt = 1;
                pre = c;
            }
        }
        res += (long) (cnt + 1) * cnt / 2;
        return (int) (res % MOD);
    }

}
