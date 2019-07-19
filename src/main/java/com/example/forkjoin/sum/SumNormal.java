package com.example.forkjoin.sum;

/**
 * @ClassName: SumNormal
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/10 09:12
 */
public class SumNormal {

    public static void main(String[] args) {
        int count  = 0;
        int[] src  = MakeArray.makeArr();
        long start = System.currentTimeMillis();
        for (int i = 0; i < src.length; i++) {
            count = count + src[i];
        }
        System.out.println("The count is " + count
                + "speng time:" + (System.currentTimeMillis() - start) + " ms");
    }
}
