package com.atguigu.crowd.exception;

/**
 * @author linlingde
 * @version 1.0
 * @className AccessForbiddenException
 * @description 用户未登录就访问受保护的资源时的异常
 * @date 2022/7/9 18:11
 **/
public class AccessForbiddenException extends RuntimeException {
    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
