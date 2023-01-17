package day20230116;

import org.junit.Test;

/**
 * @author hlam
 * @date 2023/1/16
 */
public class Solution {
    @Test
    public void test() {
        String s1 = "My name is Haley";
        String s2 = "My Haley";
        System.out.println(areSentencesSimilar(s1, s2));
    }
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] s1 = sentence1.split(" ");
        String[] s2 = sentence2.split(" ");
        if (s1.length < s2.length) {
            String[] tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        int i  = 0, j = 0;
        while (i < s2.length && s1[i].equals(s2[i])) {
            i ++;
        }
        while (j < s2.length && s1[s1.length - 1 - j].equals(s2[s2.length - 1 - j])) {
            j ++;
        }
        return i + j >= s2.length;
    }
}
