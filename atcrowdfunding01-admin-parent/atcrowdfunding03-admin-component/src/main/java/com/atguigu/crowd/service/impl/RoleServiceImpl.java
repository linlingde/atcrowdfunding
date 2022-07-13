package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.RoleExample;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linlingde
 * @version 1.0
 * @className RoleServiceImpl
 * @description
 * @date 2022/7/11 12:50
 **/
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getRolePageInfo(Integer pageNum, Integer pageSize, String keyword) {
        // 进行分页
        PageHelper.startPage(pageNum, pageSize);
        // 查询
        Page<Role> roles = roleMapper.selectRoleByKeyword(keyword);
        // 将查询结果返回
        return new PageInfo<>(roles);
    }

    @Override
    public void addRole(String name) {
        roleMapper.insert(new Role(null, name));
    }

    @Override
    public Role getRoleById(Integer id) {

        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void removeRole(List<Integer> roleIdList) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(roleIdList);
        roleMapper.deleteByExample(example);
    }
}
