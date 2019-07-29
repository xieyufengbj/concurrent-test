package com.example.lock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockTest
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/29 10:27
 */
public class LockTest {

    private static volatile int race = 0;

    //private static Lock lock = new ReentrantLock();

    private static Lock lock = new CustomLock();

    public static void increase() {
        try {
            lock.lock();
            race++;
        } finally {
            lock.unlock();
        }
    }
    private static final int THREAD_COUNT = 20;

    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                try {
                    countDownLatch.await();
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
            countDownLatch.countDown();
        }

        Thread.sleep(5000);

//        while (Thread.activeCount() == 1) {
//            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
//            ThreadInfo[] threadInfos  = threadMXBean.dumpAllThreads(false, false);
//            for (ThreadInfo threadInfo : threadInfos) {
//                System.out.println("["+ threadInfo.getThreadId() +"] " + threadInfo.getThreadName());
//            }
//            Thread.yield();
//        }
        System.out.println("******race = " + race);
    }
}
