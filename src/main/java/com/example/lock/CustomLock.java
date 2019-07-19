package com.example.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName: CustomLock
 * @Description:
 *  synchronized: jvm层实现，c语言，cas机制，重量锁
 *                字节码指令-monitorenter，monitorexit
 *  lock: jdk提供
 * @Author xieyufeng
 * @Date 2019/7/9 08:14
 */
public class CustomLock implements Lock {

    private AtomicReference<Thread> ref = new AtomicReference<>();
    private LinkedBlockingQueue<Thread> idels = new LinkedBlockingQueue<>();

    @Override
    public void lock() {

    }

    @Override
    public void unlock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
