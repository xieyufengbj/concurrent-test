package com.example.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName: AtomicStampedReferenceTest
 * @Description: 解决cas中aba问题
 * @Author xieyufeng
 * @Date 2019/7/16 09:00
 */
public class AtomicStampedReferenceTest {

    static AtomicStampedReference<String> asr = new AtomicStampedReference<>("xiexie", 0);

    public static void main(String[] args) throws InterruptedException {
        final String oriRef = asr.getReference();
        final Integer oriStamp = asr.getStamp();
        System.out.println("oriRef=" + oriRef + ", oriStamp=" + oriStamp);

        Thread rightThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": ref=" + oriRef + ",stamp=" + oriStamp
                    + asr.compareAndSet(oriRef, oriRef + "java", oriStamp, oriStamp + 1));
            }
        });

        Thread leftThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": ref=" + asr.getReference() + ",stamp=" + asr.getStamp()
                        + asr.compareAndSet(oriRef, oriRef + "c", oriStamp, oriStamp + 1));
            }
        });
        rightThread.start();
        rightThread.join();
        leftThread.start();
        leftThread.join();
        System.out.println("updateRef=" + asr.getReference() + ", updateStamp=" + asr.getStamp());
    }
}
