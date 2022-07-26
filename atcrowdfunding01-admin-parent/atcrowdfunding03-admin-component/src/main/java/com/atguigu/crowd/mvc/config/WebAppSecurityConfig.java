package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
// 想要在方法上使用@PreAuthorize("hasRole('部长')")注解,需要开启它
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 临时内存登录认证
        //builder.inMemoryAuthentication()
        //        .withUser("tom")
        //        .password("123")
        //        .roles("ADMIN");

        // 基于数据库的认证
        builder.userDetailsService(userDetailsService)
                // 密码加密
                .passwordEncoder(passwordEncoder);
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
                .antMatchers("/admin/get/page.html")
                .access("hasRole('经理') or hasAuthority('user:get')")
                //.antMatchers("/role/get/page.json")
                //.hasRole("部长")
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
                        request.setAttribute(CrowdConstant.ATTR_NAME_EXCEPTION, new Exception(CrowdConstant.MESSAGE_ACCESS_DENIED));
                        request.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(request, response);
                    }
                })
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
