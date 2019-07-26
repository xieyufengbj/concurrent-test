package com.example.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CustomAtomicInteger
 * @Description:
 *  现在有一个残缺的AtomicInteger类只实现了线程安全的：get方法和compareAndSet()方法
 *  请在理解了循环CAS后尝试自行实现它的递增方法
 * @Author xieyufeng
 * @Date 2019/7/16 08:47
 */
public class CustomAtomicInteger {
    private static AtomicInteger atomicI = new AtomicInteger(0);
    public static void increment() {
        for (;;) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }
    public static final int THREAD_COUNT = 1;
    public static void main(String[] args) {
        CustomAtomicInteger cai = new CustomAtomicInteger();

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increment();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() == 1) {
            Thread.yield();
        }
        System.out.println("atomicI=" + atomicI);
    }
}
