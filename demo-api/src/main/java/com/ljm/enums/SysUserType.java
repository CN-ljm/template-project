package com.ljm.enums;
/**
 * @title 系统用户类型
 * @desc
 *
 * @author create by jiamingl on 2021/1/24 下午9:49
 */
public enum SysUserType {
    SUPER_ADMIN("superAdmin", "超级管理员"),
    ADMIN("admin", "管理员"),
    USER("user","普通用户"),
    GUEST("guest","访客");


    private String key;

    private String desc;

    SysUserType(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getName() {
        return this.name();
    }

    public String getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }
}
