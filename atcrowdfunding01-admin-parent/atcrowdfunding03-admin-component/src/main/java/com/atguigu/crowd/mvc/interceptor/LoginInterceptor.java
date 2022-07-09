package com.atguigu.crowd.mvc.interceptor;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author linlingde
 * @version 1.0
 * @className LoginInterceptor
 * @description 登录拦截器, 检查用户是否登录, 没有登陆就跳转登录页面
 * @date 2022/7/9 18:05
 **/

// 在Interceptor里面抛出的异常使用@Exception注解捕获不到,必须在xml中配置
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取session
        HttpSession session = request.getSession();

        // 从session中取数据
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);

        // 如果等于null,抛出异常
        if (admin == null) {
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }
        // 放行
        return true;
    }
}
