package com.example;

import com.example.tools.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: CountDownLatchTest
 * @Description: 演示countdownlatch，有5个初始化的线程，6个扣除点，扣除完毕以后，主线程和业务线程才能继续自己的工作
 * @Author xieyufeng
 * @Date 2019/7/10 11:25
 */
public class CountDownLatchTest {

    static CountDownLatch latch = new CountDownLatch(6);

    /**
     * 初始化线程
     */
    private static class InitThread implements Runnable {

        @Override
        public void run() {
            System.out.println("【"+ Thread.currentThread().getId() +"】" + Thread.currentThread().getName()
                    + " ready init work ...");
            // 初始化线程完成工作了，countDown方法只扣减一次
            latch.countDown();

            for (int i = 0; i < 1; i++) {
                System.out.println("【"+ Thread.currentThread().getId() +"】" + Thread.currentThread().getName()
                    + " continue do its work");
            }
        }
    }

    /**
     * 业务线程
     */
    private static class BusiThread implements Runnable {

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 1; i++) {
                System.out.println("【"+ Thread.currentThread().getId() +"】" + Thread.currentThread().getName()
                    + " do business ...");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 单独的初始化线程，初始化分为2步，需要扣减2次
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        SleepTools.ms(1);

                        System.out.println("【"+ Thread.currentThread().getId() +"】" + Thread.currentThread().getName()
                            + "ready init work step 1st ...");

                        // 每完成一步初始化工作，扣减1次
                        latch.countDown();

                        System.out.println("begin step 2nd");
                        SleepTools.ms(1);

                        System.out.println("【"+ Thread.currentThread().getId() +"】 " + Thread.currentThread().getName()
                            + "ready init work step 2 nd");
                        latch.countDown();
                    }
                }
        , "初始化线程").start();

        new Thread(new BusiThread()).start();

        for (int i = 0; i <= 3; i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
        latch.await();
        System.out.println("Main do ites work ...");
    }
}
