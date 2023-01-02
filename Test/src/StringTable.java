/**
 * @author hlam
 * @date 2022/9/23
 */
public class StringTable {

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "a" + "b";
        String s4 = s1 + s2;
        String s5 = "ab";
        String s6 = s4.intern();

        System.out.println(s1.equals("a"));
        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);

        // 如果调换x1,x2.intern()的位置呢？JDK1.6呢？
        String x2 = new String("c") + new String("d");
        String x1 = "cd";
        x2.intern();
        System.out.println(x1 == x2);

    }

}
