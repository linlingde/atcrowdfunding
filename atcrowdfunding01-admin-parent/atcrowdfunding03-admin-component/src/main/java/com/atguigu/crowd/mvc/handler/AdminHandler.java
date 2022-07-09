package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author linlingde
 * @version 1.0
 * @className AdminHandler
 * @description 用户handler
 * @date 2022/7/9 14:47
 **/
@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/do/logout.html")
    public String doLoginOut(HttpSession session) {
        // 强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }


    @RequestMapping("/admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPassword") String userPassword,
            HttpSession session
    ) {

        // 调用Service进行登录检查
        Admin admin = adminService.getAdminByLoginAccount(loginAcct, userPassword);

        // 将用户名存储到Session中
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        // 跳转到用户页面
        return "redirect:/admin/to/main/page.html";
    }
}
