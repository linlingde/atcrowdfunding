package com.atguigu.crowd.utils;


import com.atguigu.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author linlingde
 * @version 1.0
 * @className CrowdUtil
 * @description 工具类
 * @date 2022/7/8 19:20
 **/
public class CrowdUtil {

    /**
     * 判断请求是普通请求还是Ajax请求,分别进行异常处理
     * 判断是否是Ajax请求
     * 在判断是哪种请求时,需要根据request请求头判断,所以需要引入Servlet依赖
     *
     * @param request
     * @return
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        // 1.获取消息请求头,根据Accept和X-Requested-With两个头进行判断
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        return (acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));

    }

    /**
     * 加密:原始密码使用md5加密并返回加密结果
     *
     * @param source 原密码
     * @return 加密后的密码
     */
    public static String toMd5(String source) {
        if (source == null || source.length() == 0)
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);

        // 获取MessageDigest
        try {
            // 定义算法名称
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 获取明文字符串对应的字节数组
            byte[] input = source.getBytes();

            // 执行加密
            byte[] output = messageDigest.digest(input);

            // 创建BigInteger对象
            int signum = 1;
            BigInteger integer = new BigInteger(signum, output);

            // 按照十六进制将BigInteger的值转换为字符串
            int radix = 16;
            String encode = integer.toString(radix).toUpperCase();

            // 返回
            return encode;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }


}
