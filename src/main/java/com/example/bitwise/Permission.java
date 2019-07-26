package com.example.bitwise;

/**
 * @ClassName: Permission
 * @Description: 位运算适用于 权限操作、商品属性操作
 * @Author xieyufeng
 * @Date 2019/7/26 08:59
 */
public class Permission {
    // 0001 = 1 表示是否允许查询，0-否，1-是
    private static final int ALLOW_SELECT = 1 << 0;
    private static final int ALLOW_INSERT = 1 << 1;
    private static final int ALLOW_UPDATE = 1 << 2;
    private static final int ALLOW_DELETE = 1 << 3;
    // 存储目前的权限状态
    private int flag;
    // 设置用户的权限
    public void setPer(int per) {
        flag = per;
    }
    // 增加用户的权限(1个或者多个)
    public void enable(int per) {
        flag = flag | per;
    }
    // 删除用户的权限（1个或者多个）
    public void disable(int per) {
        flag = flag & ~per;
    }
    // 判断用户的权限
    public boolean isAllow(int per) {
        return ((flag & per) == per);
    }
    // 判断用户没有权限
    public boolean isNotAllow(int per) {
        return ((flag & per) == 0);
    }
    public static void main(String[] args) {
        int flag = 15;
        Permission permission = new Permission();
        permission.setPer(flag);
        permission.disable(ALLOW_SELECT|ALLOW_UPDATE);
        System.out.println("select = " + permission.isAllow(ALLOW_SELECT));
        System.out.println("insert = " + permission.isAllow(ALLOW_INSERT));
        System.out.println("update = " + permission.isAllow(ALLOW_UPDATE));
        System.out.println("delete = " + permission.isAllow(ALLOW_DELETE));
    }
}
