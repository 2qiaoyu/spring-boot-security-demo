package com.joham.security.config;

import com.joham.security.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Security配置
 * （1) 应用 的每一个请求都需要认证。
 * (2)自动生成了一个登录表单。
 * (3)可以用 useName和 password来进行认证。
 * (4)用 户可 以注销。
 * (5)阻止了 CSRF攻击。
 * (6) SessionFixation保护。
 * (7)安全 Header集成了以下内容。
 * (8)集成了以下的 ServletAPI 的方法
 *
 * @author joham
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //内存中获取账号密码
        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("joham").password(new
        // BCryptPasswordEncoder().encode("123456")).roles("USER");

        //BCryptPasswordEncoder Spring security 5.0 新加的密码加密格式
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/blogs/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .and()
                .exceptionHandling().accessDeniedPage("/401");
        httpSecurity.logout().logoutSuccessUrl("/");
    }

    /**
     * 密码加密
     *
     * @param args
     */
    public static void main(String[] args) {
        String pwd = "123456";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encodedPassword = passwordEncoder.encode(pwd);
        log.info("【加密后的密码为：】" + encodedPassword);
    }
}
