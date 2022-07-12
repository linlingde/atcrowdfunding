package com.atguigu.crowd.exception;

/**
 * @author linlingde
 * @version 1.0
 * @className LoginAcctAlreadyInUse
 * @description 账户名已经被使用
 * @date 2022/7/11 09:02
 **/
public class LoginAcctAlreadyInUseForUpdateException extends RuntimeException {

    public LoginAcctAlreadyInUseForUpdateException() {
    }

    public LoginAcctAlreadyInUseForUpdateException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseForUpdateException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
