package day20221227;

/**
 * 2027. 转换字符串的最少操作次数
 * @author hlam
 * @date 2022/12/27
 */
public class Solution {
    /**
     * 给你一个字符串 s ，由 n 个字符组成，每个字符不是 'X' 就是 'O' 。
     *
     * 一次 操作 定义为从 s 中选出 三个连续字符 并将选中的每个字符都转换为 'O' 。注意，如果字符已经是 'O' ，只需要保持不变 。
     *
     * 返回将 s 中所有字符均转换为 'O' 需要执行的最少操作次数。
     * 输入：s = "XXOX"
     * 输出：2
     * 解释：XXOX -> OOOX -> OOOO
     * 第一次操作，选择前 3 个字符，并将这些字符转换为 'O' 。
     * 然后，选中后 3 个字符，并执行转换。最终得到的字符串全由字符 'O' 组成。
     */
    public static void main(String[] args) {
        System.out.println(minimumMoves("XXOX"));
    }

    public static int minimumMoves(String s) {
        int count = 0;
        int size = s.length();
        for (int i = 0; i < size; ++ i) {
            if (s.charAt(i) == 'X') {
                count ++;
                i += 2;
            }
        }
        return count;
    }

}
