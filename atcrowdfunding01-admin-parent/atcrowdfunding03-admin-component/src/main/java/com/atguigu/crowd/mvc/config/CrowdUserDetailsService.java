package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.AuthService;
import com.atguigu.crowd.service.api.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linlingde
 * @version 1.0
 * @className CrowdUserDetailsService
 * @description
 * @date 2022/7/26 09:36
 **/
@Component
public class CrowdUserDetailsService implements UserDetailsService {

    // Admin
    @Autowired
    private AdminService adminService;
    // role
    @Autowired
    private RoleService roleService;
    // auth
    @Autowired
    private AuthService authService;

    private Logger logger = LoggerFactory.getLogger(CrowdUserDetailsService.class);


    @Override
    public UserDetails loadUserByUsername(String loginAcct) throws UsernameNotFoundException {
        // 根据loginAccount获取admin
        Admin admin = adminService.getAdminByLoginAcct(loginAcct);

        // 获取AdminId
        Integer adminId = admin.getId();
        // 根据AdminId获取角色信息
        List<Role> roleList = roleService.getAssignedRole(adminId);
        // 根据AdminId获取权限信息
        List<String> authNameList = authService.getAssignedAuthNameByAdminId(adminId);
        // 创建集合对象存储GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 遍历roleList存入角色信息
        for (Role role : roleList) {
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }
        // 遍历authNameList放入权限信息
        for (String authName : authNameList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(simpleGrantedAuthority);
        }
        // 封装SecurityAdmin对象
        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);
        return securityAdmin;
    }
}
