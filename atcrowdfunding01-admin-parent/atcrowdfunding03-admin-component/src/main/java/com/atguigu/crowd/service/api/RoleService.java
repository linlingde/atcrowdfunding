package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

/**
 * @author linlingde
 * @version 1.0
 * @interfaceName RoleService
 * @description 角色
 * @date 2022/7/11 12:48
 **/
public interface RoleService {
    PageInfo<Role> getRolePageInfo(Integer pageNum, Integer pageSize, String keyword);

    void addRole(String name);

    Role getRoleById(Integer id);

    void updateRole(Role role);

    void deleteRoleById(Integer id);
    

}
