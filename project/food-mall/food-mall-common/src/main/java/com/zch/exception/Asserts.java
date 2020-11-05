package com.zch.exception;

import com.zch.result.IErrorCode;

/**
 * @author zch
 * @date 2020/11/5 14:38
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
