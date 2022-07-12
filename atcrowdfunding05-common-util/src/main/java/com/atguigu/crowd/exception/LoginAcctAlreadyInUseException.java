package com.atguigu.crowd.exception;

/**
 * @author linlingde
 * @version 1.0
 * @className LoginAcctAlreadyInUse
 * @description 账户名已经被使用
 * @date 2022/7/11 09:02
 **/
public class LoginAcctAlreadyInUseException extends RuntimeException {

    public LoginAcctAlreadyInUseException() {
    }

    public LoginAcctAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
