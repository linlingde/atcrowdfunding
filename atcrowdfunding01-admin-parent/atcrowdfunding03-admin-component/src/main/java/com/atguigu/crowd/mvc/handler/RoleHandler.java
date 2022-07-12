package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.RoleService;
import com.atguigu.crowd.utils.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author linlingde
 * @version 1.0
 * @className RoleHandler
 * @description role
 * @date 2022/7/11 12:51
 **/
@Controller
public class RoleHandler {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/role/get/page/info.json")
    public ResultEntity<PageInfo<Role>> getRolePageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {

        PageInfo<Role> rolePageInfo;
        try {
            rolePageInfo = roleService.getRolePageInfo(pageNum, pageSize, keyword);
            return ResultEntity.successWithData(rolePageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping("/role/add.json")
    public ResultEntity addRole(@RequestParam(value = "roleName") String roleName) {
        roleService.addRole(roleName);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/role/get.json")
    public ResultEntity getRole(@RequestParam(value = "id") Integer id) {
        Role role = roleService.getRoleById(id);
        return ResultEntity.successWithData(role);
    }

    @ResponseBody
    @RequestMapping("/role/update.json")
    public ResultEntity updateRole(Role role) {
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }


    @ResponseBody
    @RequestMapping("/role/deleteById.json")
    public ResultEntity deleteRoleById(@RequestParam(value = "id") Integer id) {
        roleService.deleteRoleById(id);
        return ResultEntity.successWithoutData();
    }
}
