package com.example;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName: CyclicBarrierTest
 * @Description: CyclicBarrier - 循环栅栏（可循环利用都屏障），作用：会让所有线程都等待完成后才会继续下一步行动。
 * @Author xieyufeng
 * @Date 2019/7/10 11:25
 */
public class CyclicBarrierTest {

    private static CyclicBarrier barrier = new CyclicBarrier(5, new CollectThread());

    // 存放子线程工作结果的容器
    private static ConcurrentHashMap<String, Long> resultMap
            = new ConcurrentHashMap<>();

    // 负责屏障开放以后的工作
    private static class CollectThread implements Runnable {

        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            resultMap.forEach((key, value) -> {
                result.append("["+ value +"]");
            });
            System.out.println("【"+ Thread.currentThread().getId()+ "】" + Thread.currentThread().getName()
                    + ": result=" + result + " then do other business...");
        }
    }

    // 工作线程
    private static class SubThread implements Runnable {

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            resultMap.put(Thread.currentThread().getId() + "", id);

            Random random = new Random();
            try {
                if (random.nextBoolean()) {
                    Thread.sleep(2000 + id);
                    System.out.println("【" + Thread.currentThread().getId() + "】" + Thread.currentThread().getName()
                        + ": do something ...");
                }
                System.out.println("【" + Thread.currentThread().getId() + "】 " + Thread.currentThread().getName()
                    + ": is await ...");
                barrier.await();
                Thread.sleep(1000 + id);
                System.out.println("【" + Thread.currentThread().getId() + "】" + Thread.currentThread().getName()
                    + ": do its business");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 4; i++) {
            Thread thread = new Thread(new SubThread());
            thread.start();
        }
    }
}
