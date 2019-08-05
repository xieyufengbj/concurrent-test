package com.example.forkjoin;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: MakeArray
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/10 09:10
 */
public class MakeArray {

    public static final int ARR_LENGTH = 1000;

    public static int[] increase() {
        int[] src = new int[ARR_LENGTH];
        AtomicLong al = new AtomicLong(0);
        for (int i = 0; i < ARR_LENGTH; i++) {
            src[i] = (int)al.getAndIncrement();
        }
        return src;
    }

    public static int[] generate() {
        int[] src = new int[ARR_LENGTH];
        Random random = new Random();
        for (int i = 0; i < ARR_LENGTH; i++) {
            src[i] = random.nextInt(10);
        }
        return src;
    }
}
