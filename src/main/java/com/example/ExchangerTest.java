package com.example;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @ClassName: ExchangeTest
 * @Description: exchanger是提供的一个用于两个工作线程之间交换数据的封装工具类，简单说：一个线程在完成一定的事物后想与另一个线程交换数据，
 *                  则第一个先拿出数据的线程会一直等待第二个线程，直到第二个线程拿着数据到来的时候才能彼此交换对应的数据。
 * @Author xieyufeng
 * @Date 2019/7/10 11:26
 */
public class ExchangerTest {

    private static final Exchanger<Set<String>> exchange
            = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        // 存放数据的容器
                        Set<String> setA = new HashSet<>();
                        try {
                            // 添加数据
                            setA.add(Thread.currentThread().getName() + "-data");
                            setA = exchange.exchange(setA);
                            // 交换后数据
                            System.out.println("【"+ Thread.currentThread().getName() +"】输出结果：" + setA);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "线程一"
        ).start();

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Set<String> setB = new HashSet<>();
                        try {
                            // 添加数据
                            setB.add(Thread.currentThread().getName() + "-data");
                            setB = exchange.exchange(setB);
                            // 交换后数据
                            System.out.println("【"+ Thread.currentThread().getName() +"】输出结果：" + setB);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "线程二"
        ).start();
    }
}
