package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Auth;

import java.util.List;

/**
 * @author linlingde
 * @version 1.0
 * @interfaceName AuthService
 * @description
 * @date 2022/7/21 09:06
 **/
public interface AuthService {
    List<Auth> getAuthList();
}
