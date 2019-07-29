package com.example.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

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
        while (!ref.compareAndSet(null, Thread.currentThread())) {
            idels.add(Thread.currentThread());
            LockSupport.park();
            idels.remove(Thread.currentThread());
        }
    }

    @Override
    public void unlock() {
        while (ref.compareAndSet(Thread.currentThread(), null)) {
            Object[] objects = idels.toArray();
            for (Object obj : objects) {
                Thread thread = (Thread) obj;
                LockSupport.unpark(thread);
            }
        }
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
