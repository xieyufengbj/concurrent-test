package com.example.rwlock;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: GoodsInfo
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/29 15:34
 */
@Data
@Builder
public class GoodsInfo {
    private final String name;
    private double totalMoney;
    private int storeNumber;

    public void changeNumber(int sellNumber) {
        this.totalMoney += sellNumber * 25;
        this.storeNumber -= sellNumber;
    }
}
