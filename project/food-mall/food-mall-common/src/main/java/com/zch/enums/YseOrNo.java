package com.zch.enums;

/**
 * @description: 是否枚举
 * @author: zch
 * @create: 2020-10-11 12:21
 */
public enum YseOrNo {
    YES(1, "是"),
    NO(0, "否");

    public final Integer type;
    public final String value;

    YseOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
