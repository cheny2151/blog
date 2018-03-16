package com.oc.javaconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.time.DateUtils;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

/**
 * 配置profiles
 * 1,xml:配置默认加载的profiles spring.profiles.default
 * 2.javaConfig:rootContext.getEnvironment().setDefaultProfiles("xxx");
 * 3,tomcat启动脚本中配置激活的profiles --> catalina.bar/sh -->添加JVM环境变量 JAVA_OPTS = -Dspring.profiles.active=prod
 */
@Configuration
public class BeanConfig {

    @Profile("dev")
    @Bean(name = "profilesBean")
    public String devBean() {
        return "dev";
    }

    @Profile("prod")
    @Bean(name = "profilesBean")
    public String prodBean() {
        return "prod";
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("usr","admin");
        map.put("exp",new Date());
        String cherry = Jwts.builder().setClaims(map).setExpiration(DateUtils.addDays(new Date(), 7))
                .signWith(SignatureAlgorithm.HS256, "cherry").compact();
        Claims cherry1 = Jwts.parser().setSigningKey("cherry").parseClaimsJws(cherry).getBody();
        System.out.println(cherry1);
    }

}
