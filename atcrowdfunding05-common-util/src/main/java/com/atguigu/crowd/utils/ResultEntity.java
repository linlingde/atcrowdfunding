package com.atguigu.crowd.utils;

/**
 * @author linlingde
 * @version 1.0
 * @className ResultEntity
 * @description Ajax中统一的数据返回格式
 * @date 2022/7/8 18:32
 **/
public class ResultEntity<T> {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";

    // 用来封装当前请求处理的结果是成功还是失败
    private String result;

    // 请求处理失败时返回的消息
    private String message;

    // 要返回的数据
    private T data;


    // 请求成功但无数据返回时
    public static <Type> ResultEntity<Type> successWithoutData() {
        return new ResultEntity<Type>(SUCCESS, null, null);
    }

    // 请求成功有数据返回时
    public static <Type> ResultEntity<Type> successWithData(Type data) {
        return new ResultEntity<Type>(SUCCESS, null, data);
    }

    // 请求失败时
    public static <Type> ResultEntity<Type> failed(String message) {
        return new ResultEntity<Type>(FAILED, message, null);
    }


    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
