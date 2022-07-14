package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Menu;

import java.util.List;

/**
 * @author linlingde
 * @version 1.0
 * @interfaceName MenuService
 * @description menu的service
 * @date 2022/7/13 10:23
 **/
public interface MenuService {
    List<Menu> getAll();

    void addMenu(Menu menu);

    void updateMenu(Menu menu);

    boolean removeMenuById(Integer id);
}
