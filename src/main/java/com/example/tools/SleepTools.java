package com.example.tools;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SleepTools
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/29 15:47
 */
public class SleepTools {
    /**
     * 按秒休眠
     * @param seconds 秒数
     */
    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }

    /**
     * 按毫秒数休眠
     * @param seconds 毫秒数
     */
    public static final void ms(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }

    /**
     * 按纳秒数休眠
     * @param seconds
     */
    public static final void ns(int seconds) {
        try {
            TimeUnit.NANOSECONDS.sleep(seconds);
        } catch (InterruptedException e) {

        }
    }
}
