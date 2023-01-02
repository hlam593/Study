package day20221230;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 855. 考场就座
 * @author hlam
 * @date 2022/12/30
 */
public class ExamRoom {
    /**
     * 在考场里，一排有N个座位，分别编号为0, 1, 2, ..., N-1。
     * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。
     * 如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
     * 返回ExamRoom(int N)类，它有两个公开的函数：其中，函数ExamRoom.seat()会返回一个int（整型数据），代表学生坐的位置；
     * 函数ExamRoom.leave(int p)代表坐在座位 p 上的学生现在离开了考场。每次调用ExamRoom.leave(p)时都保证有学生坐在座位p上。
     *
     * 输入：["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
     * 输出：[null,0,9,4,2,null,5]
     * 解释：
     * ExamRoom(10) -> null
     * seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。
     * seat() -> 9，学生最后坐在 9 号座位上。
     * seat() -> 4，学生最后坐在 4 号座位上。
     * seat() -> 2，学生最后坐在 2 号座位上。
     * leave(4) -> null
     * seat() -> 5，学生最后坐在 5 号座位上。
     */
    private TreeSet<int[]> treeSet = new TreeSet<>((a, b) -> {
       int d1 = dist(a), d2 = dist(b);
       return d1 == d2 ? a[0] - b[0] : d2 - d1;
    });
    private int n;
    private Map<Integer, Integer> left = new HashMap<>();
    private Map<Integer, Integer> right = new HashMap<>();

    public ExamRoom(int n) {
        this.n = n;
        add(new int[]{-1, n});
    }

    public int seat() {
        int[] s = treeSet.first();
        int p = (s[0] + s[1]) >> 1;
        if (s[0] == -1) {
            p = 0;
        } else if (s[1] == n) {
            p = n - 1;
        }
        del(s);
        add(new int[]{s[0], p});
        add(new int[]{p, s[1]});
        return p;
    }

    public void leave(int p) {
        int l = left.get(p), r = right.get(p);
        del(new int[]{l, p});
        del(new int[]{p, r});
        add(new int[]{l, r});
    }

    public void add(int[] s) {
        treeSet.add(s);
        left.put(s[1], s[0]);
        right.put(s[0], s[1]);
    }

    public void del(int[] s) {
        treeSet.remove(s);
        left.remove(s[1]);
        right.remove(s[0]);
    }

    private int dist(int[] s) {
        int l = s[0], r = s[1];
        return l == -1 || r == n ? r - l - 1 : (r - l) >> 1;
    }


}
