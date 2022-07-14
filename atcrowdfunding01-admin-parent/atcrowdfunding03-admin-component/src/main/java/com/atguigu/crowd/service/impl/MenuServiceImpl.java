package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.entity.MenuExample;
import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linlingde
 * @version 1.0
 * @className MenuServiceImpl
 * @description
 * @date 2022/7/13 10:23
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        return menuMapper.selectByExample(menuExample);
    }

    @Override
    public void addMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public boolean removeMenuById(Integer id) {
        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andPidEqualTo(id);

        // 查找其有没有子节点
        List<Menu> menuList = menuMapper.selectByExample(menuExample);
        // size大于0表示有子节点
        if (menuList.size() > 0) {
            return false;
        } else {
            menuMapper.deleteByPrimaryKey(id);
            return true;
        }

    }
}
