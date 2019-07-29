package com.example.rwlock;

import com.example.tools.SleepTools;

import java.util.Random;

/**
 * @ClassName: BussApp
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/29 16:00
 */
public class BussApp {
    static final int readWriteRatio = 10;
    static final int minThreadCount = 3;

    private static class ReadThread implements Runnable {
        private GoodsService goodsService;

        public ReadThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }
        @Override
        public void run() {
            long start = System.currentTimeMillis();

            for (int i = 0; i < 100; i++) {
                goodsService.getNum();
            }
            System.out.println("["+ Thread.currentThread().getId() +"]--[" + Thread.currentThread().getName()
                    + "]读取商品信息耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    private static class WriteThread implements Runnable {
        private GoodsService goodsService;

        public WriteThread(GoodsService goodsService) {
            this.goodsService = goodsService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                goodsService.setNum(random.nextInt(10));
            }
            System.out.println("["+ Thread.currentThread().getId() +"]--["+ Thread.currentThread().getName()
                    +"]销售商品信息耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }

    public static void main(String[] args) {
        GoodsInfo goodsInfo = new GoodsInfo("CUP", 100000, 100000);
        GoodsService goodsService = new UseRwLock(goodsInfo);
        for (int i = 0; i < minThreadCount; i++) {
            Thread w = new Thread(new WriteThread(goodsService));
            for (int j = 0; j < readWriteRatio; j++) {
                Thread r = new Thread(new ReadThread(goodsService));
                r.start();
            }
            SleepTools.ms(100);
            w.start();
        }
    }
}
