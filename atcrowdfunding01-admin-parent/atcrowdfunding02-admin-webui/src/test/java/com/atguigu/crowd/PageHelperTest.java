package com.atguigu.crowd;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author linlingde
 * @version 1.0
 * @className PageHelperTest
 * @description pagehelper测试
 * @date 2022/7/10 10:56
 **/

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-persist-tx.xml", "classpath:spring-persist-tx.xml"})
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class PageHelperTest {


    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void testSelectAdmin() {
        PageHelper.startPage(1, 5);
        List<Admin> list = adminMapper.selectAdminByKeyword("");
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        System.err.println(pageInfo);
        //System.err.println(list.get(0));
    }


}
