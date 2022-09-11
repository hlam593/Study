package org.hl.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试 CountDownLatch 倒计时器
 * 同步工具类，用来协调多个线程之间的同步
 * @author hlam
 * @date 2022/8/28
 */
public class CountDownLatchExample {

    // 处理文件的数量
    private static final int threadCount = 6;

    public static void main(String[] args) throws InterruptedException {

        // 创建一个具有固定线程数量的线程池对象（推荐使用构造方法创建）
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            threadPool.execute(() -> {
                try {
                    //处理文件的业务操作
                    System.out.println(Thread.currentThread().getName() + "----->" + threadNum);
                    //......
                } finally {
                    //表示一个文件已经被完成
                    countDownLatch.countDown();
                }

            });
        }
        System.out.println("count ---->" + countDownLatch.getCount());
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }

}
