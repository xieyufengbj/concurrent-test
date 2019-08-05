package com.example.forkjoin;

import com.example.tools.SleepTools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName: SumArray
 * @Description:
 *  Fork/Join：体现了"分而治之"，就是在必要的情况下，将一个大任务，进行拆分（fork）成若干个小人物（拆到不可再拆时），
 *             再将一个个的小任务运算的结果进行join汇总；
 * @Author xieyufeng
 * @Date 2019/7/9 09:18
 */
public class SumArray {

    private static class SumTask extends RecursiveTask<Integer> {
        private final static int THRESHOLD = MakeArray.ARR_LENGTH / 10;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                int count = 0;
                for (int i = fromIndex; i <= toIndex; i++) {
                    // 模拟复杂计算
                    SleepTools.ns(1);
                    count += src[i];
                }
                return count;
            } else {
                int mid = (toIndex + fromIndex) / 2;
                SumTask left  = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid + 1, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.generate();
        long start = System.currentTimeMillis();
        // 普通计算
        long count = 0;
        for (int i = 0, len = src.length; i < len; i++) {
            // 模拟复杂计算
            SleepTools.ns(1);
            count+= src[i];
        }
        System.out.println("count=" + count + "普通计算完成耗时：" + (System.currentTimeMillis() - start) + " ms");

        SumTask task = new SumTask(src, 0, src.length - 1);
        start = System.currentTimeMillis();
        // 同步调用
        pool.invoke(task);
        System.out.println("count="+ task.join() +" fork-join完成计算耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
}
