/**
 * @author hlam
 * @date 2022/11/15
 */
public class Test {

    public static void main(String[] args) {
//        CustomEntity.CustomEntityBuilder builder = CustomEntity.builder();
//        System.out.println(builder.roleId("1").build());
//        System.out.println(builder.roleId("2").build());
        repeatedCharacter("abccbaacz");
    }

    public static char repeatedCharacter(String s) {
        int mask = 0;
        for(int i = 0; ; i ++) {
            char c  = s.charAt(i);
            if ((mask >> (c - 'a') & 1) == 1) {
                return c;
            }
            mask |= 1 << (c - 'a');
        }
//        int[] cnt = new int[26];
//        for(int i = 0; ; i ++) {
//            char c  = s.charAt(i);
//            if (++ cnt[c - 'a'] == 2) {
//                return c;
//            }
//        }
    }
}
