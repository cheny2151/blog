package javaconfig;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * 配置profiles
 * 1,xml中配置默认加载的profiles spring.profiles.default
 * 2,tomcat启动脚本中配置激活的profiles --> catalina.bar/sh -->添加JVM环境变量 JAVA_OPTS = -Dspring.profiles.active=prod
 */
@Configurable
public class BeanConfig {

    @Profile("dev")
    @Bean(name = "profilesBean_dev")
    public String devBean() {
        return "dev";
    }

    @Profile("prod")
    @Bean(name = "profilesBean_prod")
    public String prodBean() {
        return "prod";
    }

}
