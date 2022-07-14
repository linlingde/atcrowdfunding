package com.atguigu.crowd.exception;

/**
 * @author linlingde
 * @version 1.0
 * @className Remove_Menu_Failed_Exception
 * @description
 * @date 2022/7/14 15:25
 **/
public class RemoveMenuFailedException extends RuntimeException {
    public RemoveMenuFailedException() {
    }

    public RemoveMenuFailedException(String message) {
        super(message);
    }

    public RemoveMenuFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoveMenuFailedException(Throwable cause) {
        super(cause);
    }

    public RemoveMenuFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
