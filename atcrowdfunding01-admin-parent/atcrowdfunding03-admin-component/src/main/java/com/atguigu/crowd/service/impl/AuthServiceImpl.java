package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.AuthExample;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author linlingde
 * @version 1.0
 * @className AuthServiceImpl
 * @description
 * @date 2022/7/21 09:06
 **/
@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAuthList() {
        AuthExample example = new AuthExample();
        AuthExample.Criteria criteria = example.createCriteria();
        List<Auth> list = authMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer id) {

        return authMapper.AssignedAuthIdByRoleId(id);
    }

    @Override
    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {
        // 先获取roleId
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);

        // 根据roleId删除旧的关联规则数据
        authMapper.deleteOldRelationship(roleId);

        // 再获取authId
        List<Integer> authIdList = map.get("authIdArray");
        // 判断authIdList是否有效
        if (authIdList.size() > 0 && authIdList != null) {
            authMapper.insertNewRelationship(roleId, authIdList);
        }
    }

    @Override
    public List<String> getAssignedAuthNameByAdminId(Integer adminId) {

        return authMapper.selectAssignedAuthNameByAdminId(adminId);
    }
}
