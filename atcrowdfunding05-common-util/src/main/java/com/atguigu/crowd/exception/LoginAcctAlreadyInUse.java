package com.atguigu.crowd.exception;

/**
 * @author linlingde
 * @version 1.0
 * @className LoginAcctAlreadyInUse
 * @description 账户名已经被使用
 * @date 2022/7/11 09:02
 **/
public class LoginAcctAlreadyInUse extends RuntimeException {
    public LoginAcctAlreadyInUse() {
    }

    public LoginAcctAlreadyInUse(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUse(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUse(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUse(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
