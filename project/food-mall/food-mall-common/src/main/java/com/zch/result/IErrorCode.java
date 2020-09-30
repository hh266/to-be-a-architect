package com.zch.result;

/**
 * 返回码
 * @author zch
 * @date 2020/9/30 14:08
 */
public interface IErrorCode {
    /**
     * 状态码
     * @return
     */
    long getCode();

    /**
     * 提示信息
     * @return
     */
    String getMessage();
}
