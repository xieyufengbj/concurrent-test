package com.example;

import com.example.tools.SleepTools;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: FutureTest
 * @Description:
 * @Author xieyufeng
 * @Date 2019/8/12 08:08
 */
public class FutureTest {

    /**
     * 实现Callable接口，允许有返回值
     */
    private static class UseCallable implements Callable<Integer> {

        private int sum;

        @Override
        public Integer call() throws Exception {
            System.out.println("callable 子线程开始计算");
            Thread.sleep(2000);

            for (int i = 0; i < 5000; i++) {
                sum += i;
            }

            System.out.println("callable 子线程计算完成，结果=" + sum);
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        UseCallable useCallable = new UseCallable();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(useCallable);

        new Thread(futureTask).start();

        Random random = new Random();
        SleepTools.second(1);
        if (random.nextBoolean()) {
            System.out.println("Get UserCallable result = " + futureTask.get());
        } else {
            System.out.println("中断计算");
            futureTask.cancel(true);
        }
    }
}
