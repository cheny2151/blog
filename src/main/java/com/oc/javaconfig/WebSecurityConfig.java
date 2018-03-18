package com.oc.javaconfig;

import com.oc.filter.JsonWebTokenFilter;
import com.oc.service.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置
 * http://blog.csdn.net/carrie__yang/article/details/72867962
 */
@Configuration
//启用web安全性
@EnableWebSecurity
@PropertySource(value = {"classpath:security.properties"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl tokenUserDetailsService) {
        this.userDetailsService = tokenUserDetailsService;
    }

    /**
     * 通过重载,配置user-detail服务(用户储存)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     *
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
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/blogger/**").hasRole("BLOGGER")
                .anyRequest().permitAll()
                .and().csrf().disable()                                 //禁用spring跨域处理
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //spring 永不创建session
        http.addFilterBefore(jsonWebTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 加密算法:BCrypt
     */
    @Bean("passwordEncoder")
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("jsonWebTokenFilter")
    public JsonWebTokenFilter jsonWebTokenFilter() {
        return new JsonWebTokenFilter();
    }

}
