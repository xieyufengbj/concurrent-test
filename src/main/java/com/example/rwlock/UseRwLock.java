package com.example.rwlock;

import com.example.tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: UserRwLock
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/29 15:52
 */
public class UseRwLock implements GoodsService {

    private GoodsInfo goodsInfo;

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    @Override
    public GoodsInfo getNum() {
       readLock.lock();
       try {
           SleepTools.ms(5);
           return this.goodsInfo;
       } finally {
            readLock.unlock();
       }
    }

    @Override
    public void setNum(int number) {
        writeLock.lock();
        try {
            SleepTools.ms(5);
            this.goodsInfo.changeNumber(number);
        } finally {
            writeLock.unlock();
        }
    }
}
