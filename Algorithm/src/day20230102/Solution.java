package day20230102;

import java.util.PriorityQueue;

/**
 * 1801. 积压订单中的订单总数
 * @author hlam
 * @date 2023/1/2
 */
public class Solution {

    /**
     * 给你一个二维整数数组 orders ，其中每个 orders[i] = [pricei, amounti, orderTypei] 表示有 amounti 笔类型为 orderTypei 、价格为pricei 的订单。
     *
     * 订单类型 orderTypei 可以分为两种：
     *
     * 0 表示这是一批采购订单 buy
     * 1 表示这是一批销售订单 sell
     * 注意，orders[i] 表示一批共计 amounti 笔的独立订单，这些订单的价格和类型相同。对于所有有效的 i ，由 orders[i] 表示的所有订单提交时间均早于 orders[i+1] 表示的所有订单。
     *
     * 存在由未执行订单组成的 积压订单 。积压订单最初是空的。提交订单时，会发生以下情况：
     *
     * 如果该订单是一笔采购订单 buy ，则可以查看积压订单中价格 最低 的销售订单 sell 。如果该销售订单 sell 的价格 低于或等于 当前采购订单 buy 的价格，则匹配并执行这两笔订单，并将销售订单 sell 从积压订单中删除。否则，采购订单 buy 将会添加到积压订单中。
     * 反之亦然，如果该订单是一笔销售订单 sell ，则可以查看积压订单中价格 最高 的采购订单 buy 。如果该采购订单 buy 的价格 高于或等于 当前销售订单 sell 的价格，则匹配并执行这两笔订单，并将采购订单 buy 从积压订单中删除。否则，销售订单 sell 将会添加到积压订单中。
     * 输入所有订单后，返回积压订单中的 订单总数 。由于数字可能很大，所以需要返回对 109 + 7 取余的结果。
     *
     * 输入：orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
     * 输出：6
     * 解释：输入订单后会发生下述情况：
     * - 提交 5 笔采购订单，价格为 10 。没有销售订单，所以这 5 笔订单添加到积压订单中。
     * - 提交 2 笔销售订单，价格为 15 。没有采购订单的价格大于或等于 15 ，所以这 2 笔订单添加到积压订单中。
     * - 提交 1 笔销售订单，价格为 25 。没有采购订单的价格大于或等于 25 ，所以这 1 笔订单添加到积压订单中。
     * - 提交 4 笔采购订单，价格为 30 。前 2 笔采购订单与价格最低（价格为 15）的 2 笔销售订单匹配，从积压订单中删除这 2 笔销售订单。第 3 笔采购订单与价格最低的 1 笔销售订单匹配，销售订单价格为 25 ，从积压订单中删除这 1 笔销售订单。积压订单中不存在更多销售订单，所以第 4 笔采购订单需要添加到积压订单中。
     * 最终，积压订单中有 5 笔价格为 10 的采购订单，和 1 笔价格为 30 的采购订单。所以积压订单中的订单总数为 6 。
     */

    public static void main(String[] args) {
        System.out.println(getNumberOfBacklogOrders(new int[][] {{10,5,0}, {15,2,1}, {25,1,1},{30,4,0}}));
    }

    public static int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> buy = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> sell = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] order : orders) {
            // 价格
            int p = order[0],
                    // 数量
                    a = order[1],
                    // 类型
                    t = order[2];
            // 采购订单
            if (t == 0) {
                // 采购数量大于0，销售队列非空，销售队列里第一个的价格需要小于等于采购价格
                while (a > 0 && !sell.isEmpty() && sell.peek()[0] <= p) {
                    // 删除销售队列堆顶元素
                    int[] q = sell.poll();
                    // x = 销售价格，y = 销售数量
                    int x = q[0], y = q[1];
                    // 当采购数量 >= 销售数量时
                    if (a >= y) {
                        a -= y;
                    } else {
                        // 进入销售队列
                        sell.offer(new int[] {x, y - a});
                        a = 0;
                    }
                }
                // 余下的采购订单重新进入采购队列
                if (a > 0) {
                    buy.offer(new int[] {p , a});
                }
            } else {
                while (a > 0 && !buy.isEmpty() && buy.peek()[0] >= p) {
                    int[] q = buy.poll();
                    int x = q[0], y = q[1];
                    if (a >= y) {
                        a -= y;
                    } else {
                        buy.offer(new int[] {x, y - a});
                        a = 0;
                    }
                }
                if (a > 0) {
                    sell.offer(new int[] {p , a});
                }
            }
        }
        long ans = 0;
        final int mod = (int) 1e9 + 7;
        while (!buy.isEmpty()) {
            ans += buy.poll()[1];
        }
        while (!sell.isEmpty()) {
            ans += sell.poll()[1];
        }
        return (int) (ans % mod);
    }
}
