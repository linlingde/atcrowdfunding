package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.AuthExample;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
