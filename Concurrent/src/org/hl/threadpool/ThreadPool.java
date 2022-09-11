package org.hl.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author hlam
 * @date 2022/8/19
 */
public class ThreadPool {

    public static void main(String[] args) {

        AtomicStampedReference<Integer> arf = new AtomicStampedReference<>(0, 0);

        ExecutorService service = new ThreadPoolExecutor(5,
                10,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        Future task = service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("task");
            }
        });


    }

}
