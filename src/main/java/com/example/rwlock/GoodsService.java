package com.example.rwlock;

/**
 * @ClassName: GoodsService
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/29 15:44
 */
public interface GoodsService {
    GoodsInfo getNum();//获得商品的信息
    void setNum(int number);//设置商品的数量
}
