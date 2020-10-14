package com.zch.enums;

/**
 * 商品评论等级
 *
 * @author zch
 * @date 2020/10/14 16:22
 */
public enum ItemCommentLevel {
    goodLevel(1,"好评"),
    normalLevel(2,"中评"),
    badLevel(3,"差评");

    public final Integer type;
    public final String value;

    ItemCommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
