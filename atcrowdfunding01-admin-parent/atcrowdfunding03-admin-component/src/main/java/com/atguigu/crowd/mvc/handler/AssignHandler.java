package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author linlingde
 * @version 1.0
 * @className AssignHandler
 * @description assign
 * @date 2022/7/14 16:37
 **/
@Controller
public class AssignHandler {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    //    assign/to/role/page/${admin.id}/${requestScope.pageInfo.pageNum}/${param.keyword}.html
    @RequestMapping("/assign/to/role/page/{id}/{pageNum}/{keyword}.html")
    public String toAssignRolePage(
            @PathVariable("id") Integer id,
            @PathVariable("pageNum") String pageNum,
            @PathVariable("keyword") String keyword,
            ModelMap modelMap
    ) {
        // 查询已分配的角色
        List<Role> assignedRoleList = roleService.getAssignedRole(id);

        // 查询未分配的角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(id);

        // 存入模型
        modelMap.addAttribute("keyword", keyword);
        modelMap.addAttribute("pageNum", pageNum);
        modelMap.addAttribute("adminId", id);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_ASSIGNED_ROLE_LIST, assignedRoleList);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_UN_ASSIGNED_ROLE_LIST, unAssignedRoleList);
        return "assign-role";
    }

    @RequestMapping("assign/do/role/assign.html")
    public String saveAdminRole(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList
    ) {
        // 先根据adminId删除所有数据
        adminService.removeAdminRoleShip(adminId);
        // 判断roleList中是否有值
        if (roleIdList != null && roleIdList.size() > 0)
            // 执行添加
            adminService.saveAdminRoleShip(adminId, roleIdList);

        return "redirect:/admin/get/page.html?keyword=" + keyword + "&pageNum=" + pageNum;
    }
}
