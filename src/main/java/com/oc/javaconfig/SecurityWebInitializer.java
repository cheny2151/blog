package com.oc.javaconfig;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 注册springSecurityChain过滤器
 * 使用mvc时,将WebSecurityConfig加入到servlet容器配置的getRootConfig中。
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

}
