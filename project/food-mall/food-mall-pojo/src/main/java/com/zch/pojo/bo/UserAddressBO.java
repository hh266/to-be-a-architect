package com.zch.pojo.bo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @description: 前台传入的用户地址
 * @author: zch
 * @create: 2020-10-19 21:32
 */
public class UserAddressBO {

    @NotEmpty
    @ApiModelProperty(value = "用户Id", notes = "用户id", required = true)
    private String userId;

    @NotEmpty
    @ApiModelProperty(value = "收件人姓名", notes = "收件人姓名", required = true)
    private String receiver;

    @NotEmpty
    @ApiModelProperty(value = "收件人手机号", notes = "收件人手机", required = true)
    private String mobile;

    @NotEmpty
    @ApiModelProperty(value = "省份", notes = "收件人省份", required = true)
    private String prov;

    @NotEmpty
    @ApiModelProperty(value = "城市", notes = "收件人城市", required = true)
    private String city;

    @NotEmpty
    @ApiModelProperty(value = "县区", notes = "收件人县区", required = true)
    private String district;

    @NotEmpty
    @ApiModelProperty(value = "详细地址", notes = "收件人详细地址", required = true)
    private String detail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
