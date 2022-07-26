package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author linlingde
 * @version 1.0
 * @className SecurityAdmin
 * @description
 * @date 2022/7/26 09:24
 **/
public class SecurityAdmin extends User {
    private static final long serialVersionUID = 1L;

    // 原始Admin对象,包含Admin对象全部属性
    private Admin originalAdmin;

    public SecurityAdmin(
            // 原始Admin对象
            Admin originalAdmin,
            // 创建角色,权限集合等信息
            List<GrantedAuthority> authorities) {

        // 调用父类构造器
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);
        // 给本类中的originalAdmin赋值
        this.originalAdmin = originalAdmin;

        // 将密码擦除
        this.originalAdmin.setUserPswd(null);
    }

    // 对外提供获取原始Admin对象的get方法


    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
