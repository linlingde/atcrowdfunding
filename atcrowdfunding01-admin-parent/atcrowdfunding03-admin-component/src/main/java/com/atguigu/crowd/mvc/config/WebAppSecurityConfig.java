package com.atguigu.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author linlingde
 * @version 1.0
 * @className WebAppSecurityConfig
 * @description
 * @date 2022/7/24 10:14
 **/
// 表示当前类是一个配置类
@Configuration
// 启用web环境下Security规则
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
}
