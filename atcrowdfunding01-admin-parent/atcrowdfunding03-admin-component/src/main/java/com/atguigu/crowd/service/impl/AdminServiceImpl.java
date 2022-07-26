package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.entity.AdminExample.Criteria;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseException;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.utils.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    // 保存Service时加密
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String saveAdmin(Admin admin) {
        // 将密码加密
        String userPswd = admin.getUserPswd();
        //userPswd = CrowdUtil.toMd5(userPswd);
        // 加密
        userPswd = passwordEncoder.encode(userPswd);
        admin.setUserPswd(userPswd);
        // 设置时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        admin.setCreateTime(createTime);

        int insert = 0;
        try {
            insert = adminMapper.insert(admin);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException)
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACC_ALREADY_IN_USED);
        }
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

    /**
     * @param keyword  查询关键词
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public PageInfo<Admin> getAdminPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1. 调用PageHelper静态方法开启分页
        PageHelper.startPage(pageNum, pageSize);

        // 2. 执行查询
        List<Admin> adminList = adminMapper.selectAdminByKeyword(keyword);

        // 3. 封装到pageInfo对象中
        return new PageInfo<>(adminList);
    }

    @Override
    public void removeAdmin(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Admin findAdminById(Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        try {
            // 表示有选择的更新,对于null值不更新
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException)
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ACC_ALREADY_IN_USED);
        }
    }

    @Override
    public void removeAdminRoleShip(Integer id) {
        adminMapper.deleteAdminRoleShipByAdminId(id);
    }

    @Override
    public void saveAdminRoleShip(Integer id, List<Integer> roleIdList) {
        adminMapper.insertAdminRoleShip(id, roleIdList);
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct) {
        AdminExample adminExample = new AdminExample();
        Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        Admin admin = adminList.get(0);

        return admin;
    }

}