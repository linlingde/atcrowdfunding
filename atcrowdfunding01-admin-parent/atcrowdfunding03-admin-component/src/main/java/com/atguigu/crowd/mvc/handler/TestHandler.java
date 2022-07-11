package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.utils.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author linlingde
 * @version 1.0
 * @className TestHandler
 * @description
 * @date 2022/7/8 09:33
 **/
@Controller
public class TestHandler {

    private static final Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @Autowired
    private AdminService adminService;

    // 普通请求
    @RequestMapping("/test/ssm")
    public String testSsm(ModelMap modelMap, HttpServletRequest request) {
        //isRequest(request);

        //String s = null;
        //logger.info("s.length()" + s.length());

        List<Admin> admins = adminService.getAll();


        modelMap.addAttribute("admins", admins);

        //System.out.println(1/0);

        return "target";

    }

    // Ajax请求
    @RequestMapping("/send/array.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> args, HttpServletRequest request) {
        //isRequest(request);
        String s = null;
        logger.info("s.length()" + s.length());
        for (Integer arg : args) {
            System.out.println(arg);
        }

        return "target";

    }

    private static void isRequest(HttpServletRequest request) {
        boolean b = CrowdUtil.judgeRequestType(request);
        if (b)
            logger.info("is Ajax");
        else
            logger.info("not is Ajax");

    }

}
