package day20230115;

/**
 * @author hlam
 * @date 2023/1/15
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(minMaxGame(new int[]{70,38,21,22}));
    }
    public static int minMaxGame(int[] nums) {
        while (true) {
            int n = nums.length;
            if (n == 1) {
                return nums[0];
            }
            int[] newNums = new int[n / 2];
            for (int i = 0; i < n / 2; i ++) {
                int x = nums[i * 2];
                int y = nums[i * 2 + 1];
                if ((i & 1) == 0) {
                    newNums[i] = Math.min(x, y);
                } else {
                    newNums[i] = Math.max(x, y);
                }
            }
            nums = newNums;
        }
    }
}
