package com.zch.pojo.bo.center;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 前台提交的商品评论
 * @author zch
 */
public class OrderItemsCommentBO {

    @NotBlank(message = "商品id不能为空")
    private String itemId;

    @NotBlank(message = "商品名称不能为空")
    private String itemName;

    @NotBlank(message = "商品规格id不能为空")
    private String itemSpecId;

    @NotBlank(message = "商品规格名称不能为空")
    private String itemSpecName;

    @Min(value = 0, message = "评论等级选择不正确")
    @Max(value = 2, message = "评论等级选择不正确")
    private Integer commentLevel;

    @NotBlank(message = "商评论内容不能为空")
    private String content;


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpecId() {
        return itemSpecId;
    }

    public void setItemSpecId(String itemSpecId) {
        this.itemSpecId = itemSpecId;
    }

    public String getItemSpecName() {
        return itemSpecName;
    }

    public void setItemSpecName(String itemSpecName) {
        this.itemSpecName = itemSpecName;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "OrderItemsCommentBO{" +
                "commentLevel=" + commentLevel +
                ", content='" + content + '\'' +
                '}';
    }
}