package com.atguigu.crowd;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author linlingde
 * @version 1.0
 * @className CrowdTest
 * @description 测试数据库连接池配置
 * @date 2022/7/7 14:35
 **/

// Spring整合junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdTest {

    Logger logger;


    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Before
    public void Before() {
        logger = LoggerFactory.getLogger(CrowdTest.class);
    }

    @Test
    public void testTx() {
        Admin admin = new Admin(null, "LLD", "123", "lld", "114@qq.com", null);
        String s = adminService.saveAdmin(admin);
        logger.info(s);
    }


    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void testInsertAdmin() {
        Admin admin = new Admin(null, "lld", "lld", "lld", "114@qq.com", "2022-07-07");

        int insert = adminMapper.insert(admin);
        // sout本质上是一个IO操作,通常IO操作是比较消耗性能的
        // 如果使用日志系统,就可以通过日志的级别批量控制信息的打印
        System.out.println(insert);
    }

    @Test
    public void testLog() {

        // 把打印日志的类传进去,
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        logger.info("debug 级别日志");

    }

    @Test
    public void testInsertAdmins() {
        for (int i = 0; i < 300; i++) {
            Admin admin = new Admin(null, "lld" + i, "lld" + i, "lld" + i, "114@qq.com" + i, "2022-07-07");
            adminMapper.insert(admin);
        }
    }

    @Autowired
    RoleMapper roleMapper;

    @Test
    public void testInsertRole() {
        for (int i = 0; i < 300; i++) {
            Role role = new Role(null, "lld" + i);
            roleMapper.insert(role);

        }
    }

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testRemoveMenu() {
        menuMapper.deleteByPrimaryKey(1);
    }
}
