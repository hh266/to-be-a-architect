package com.zch.pojo.bo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @description: 提交订单的数据BO
 * @author: zch
 * @create: 2020-10-20 20:53
 */
public class SubmitOrderBO {

    @NotEmpty
    @ApiModelProperty(value = "用户Id", notes = "用户id", required = true)
    private String userId;

    @NotEmpty
    @ApiModelProperty(value = "商品规格ids", notes = "商品规格ids", required = true)
    private String itemSpecIds;

    @NotEmpty
    @ApiModelProperty(value = "用户地址Id", notes = "用户地址Id", required = true)
    private String addressId;

    @NotEmpty
    @ApiModelProperty(value = "支付方式", notes = "支付方式", required = true)
    private Integer payMethod;

    @NotEmpty
    @ApiModelProperty(value = "买家留言", notes = "买家留言", required = true)
    private String leftMsg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemSpecIds() {
        return itemSpecIds;
    }

    public void setItemSpecIds(String itemSpecIds) {
        this.itemSpecIds = itemSpecIds;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getLeftMsg() {
        return leftMsg;
    }

    public void setLeftMsg(String leftMsg) {
        this.leftMsg = leftMsg;
    }
}
