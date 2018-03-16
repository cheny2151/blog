package com.oc.javaconfig;

import com.oc.service.TokenUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;

/**
 * Spring Security配置
 */
@Configuration
//启用web安全性
@EnableWebSecurity
@PropertySource(value = {"classpath:security.properties"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private final Environment env;

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(DataSource dataSource, Environment env, TokenUserDetailsService tokenUserDetailsService) {
        this.dataSource = dataSource;
        this.env = env;
        this.userDetailsService = tokenUserDetailsService;
    }

    /**
     * 通过重载,配置user-detail服务(用户储存)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 通过重载,配置Spring Security的Filter链
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 通过重载,配置如何通过拦截器保护请求
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/test").authenticated()
                .anyRequest().permitAll()
                .and().csrf().disable()                                 //禁用spring跨域处理
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //spring 永不创建session
                .and().formLogin()
                .and().httpBasic();
    }

}
