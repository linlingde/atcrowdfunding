package com.atguigu.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("tom")
                .password("123")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/admin/to/login/page.html")
                .permitAll()
                .antMatchers("/bootstrap/**")
                .permitAll()
                .antMatchers("/bootstrap/**")
                .permitAll()
                .antMatchers("/crowd/**")
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/fonts/**")
                .permitAll()
                .antMatchers("/img/**")
                .permitAll()
                .antMatchers("/jquery/**")
                .permitAll()
                .antMatchers("/layer/**")
                .permitAll()
                .antMatchers("/script/**")
                .permitAll()
                .antMatchers("/ztree/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                // 开启表单登录
                .formLogin()
                // 跳转登录页面地址
                .loginPage("/admin/to/login/page.html")
                // 处理登录请求地址
                .loginProcessingUrl("/security/do/login.html")
                // 登录成功跳转的页面
                .defaultSuccessUrl("/admin/to/main/page.html")
                // 账号
                .usernameParameter("loginAcct")
                // 密码
                .passwordParameter("userPswd")
                .and()
                // 退出登录
                .logout()
                // 退出登录地址
                .logoutUrl("/security/do/logout.html")
                // 退出登录后跳转的页面
                .logoutSuccessUrl("/admin/to/login/page.html");

    }
}
