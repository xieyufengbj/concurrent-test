package com.example.atomic;

import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: AtomicReferenceTest
 * @Description:
 * @Author xieyufeng
 * @Date 2019/7/16 08:20
 */
public class AtomicReferenceTest {
    static AtomicReference<UserInfo> reference = new AtomicReference<UserInfo>();
    public static void main(String[] args) {
        UserInfo user = new UserInfo("鱼", 23);
        reference.set(user);
        UserInfo update = new UserInfo("丰", 25);
        reference.compareAndSet(user, update);
        System.out.println("引用操作类：" + reference.get().getName() + "====" + reference.get().getAge());
        System.out.println("引用类：" + user.getName() + "====" + user.getAge());
    }

    @Data
    public static class UserInfo {
        private String name;
        private int age;

        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
