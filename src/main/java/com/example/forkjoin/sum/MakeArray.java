package com.example.forkjoin.sum;

import java.util.Random;

/**
 * @ClassName: MakeArray
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/10 09:10
 */
public class MakeArray {

    public static final int ARRAY_LENGTH = 100000000;

    public static int[] makeArr() {
        int[] result = new int[ARRAY_LENGTH];
        Random random = new Random();
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            result[i] = random.nextInt(ARRAY_LENGTH * 3);
        }
        return result;
    }
}
