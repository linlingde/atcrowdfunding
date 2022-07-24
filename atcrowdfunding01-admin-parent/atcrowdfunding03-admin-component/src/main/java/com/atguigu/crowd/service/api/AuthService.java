package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @author linlingde
 * @version 1.0
 * @interfaceName AuthService
 * @description
 * @date 2022/7/21 09:06
 **/
public interface AuthService {
    List<Auth> getAuthList();

    List<Integer> getAssignedAuthIdByRoleId(Integer id);

    void saveRoleAuthRelationship(Map<String, List<Integer>> map);
}
