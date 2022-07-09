package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.entity.AdminExample.Criteria;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.utils.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author linlingde
 * @version 1.0
 * @className AdminServiceImpl
 * @description AdminService实现
 * @date 2022/7/7 20:18
 **/
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String saveAdmin(Admin admin) {
        int insert = adminMapper.insert(admin);
        return insert == 0 ? "保存失败" : "保存成功";
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAccount(String loginAcct, String userPassword) {
        // 根据用登录账号查询密码
        AdminExample adminExample = new AdminExample();
        Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // 查询出Admin为null,说明用户名错误或者该用户还未注册
        if (admins == null || admins.size() == 0)
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        if (admins.size() > 1)
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_ACCOUNT_UNIQUE);
        Admin admin = admins.get(0);
        if (admin == null)
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        // 查询出Admin不为null,但密码不匹配,说明用户名或密码错误
        String adminUserPswdFromDB = admin.getUserPswd();
        String adminUserPswdFromForm = CrowdUtil.toMd5(userPassword);
        if (!Objects.equals(adminUserPswdFromDB, adminUserPswdFromForm))
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        // 查询出Admin不为null,密码也匹配,登录成功
        return admin;
    }
}
