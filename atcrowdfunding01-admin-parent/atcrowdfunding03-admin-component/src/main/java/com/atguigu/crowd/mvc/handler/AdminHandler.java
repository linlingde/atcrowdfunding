package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

    private static final Logger logger = LoggerFactory.getLogger(TestHandler.class);

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
            HttpSession session) {

        // 调用Service进行登录检查
        Admin admin = adminService.getAdminByLoginAccount(loginAcct, userPassword);

        // 将用户名存储到Session中
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        // 跳转到用户页面
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            ModelMap modelMap) {

        logger.info(pageNum.toString());

        // 调用Service方法获取PageInfo对象
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        logger.info(pageInfo.toString());
        // 将查询到的数据放到modelMap中
        modelMap.put(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);

        return "admin-page";
    }

    @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String removeAdmin(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword
    ) {

        adminService.removeAdmin(adminId);


        return "redirect:/admin/get/page.html?keyword=" + keyword + "&pageNum=" + pageNum;
    }

    @RequestMapping("admin/add/user.html")
    public String addAdmin(Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }
}
