package com.oc.javaconfig.springMVC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * MVC配置
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.oc"}, useDefaultFilters = false, includeFilters = {@ComponentScan.Filter({Controller.class})})
public class WebServletConfig implements WebMvcConfigurer {

    /**
     * 静态资源由WEB服务器默认的Servlet来处理
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor = new OpenEntityManagerInViewInterceptor();
        registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor).addPathPatterns("/**");
    }


    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false);
        configurer.favorPathExtension(false);
        configurer.ignoreAcceptHeader(false);
        configurer.mediaType("html", MediaType.TEXT_HTML);
        configurer.mediaType("json", MediaType.APPLICATION_JSON_UTF8);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);
        configurer.mediaType("*", MediaType.ALL);
    }

    /**
     * 配置视图解析器
     *
     * @return
     */
    @Bean(name = "viewResolver")
    public FreeMarkerViewResolver registerFreeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setViewClass(FreeMarkerView.class);
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setExposeRequestAttributes(true);
        viewResolver.setExposeSessionAttributes(true);
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setRequestContextAttribute("request");
        viewResolver.setCache(true);
        viewResolver.setOrder(0);
        return viewResolver;
    }

    @Bean(name = "requestMappingHandlerAdapter")
    public RequestMappingHandlerAdapter registerRequestMappingHandlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }

    @Bean(name = "requestMappingHandlerMapping")
    public RequestMappingHandlerMapping registerRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    /**
     * 上传解析器
     *
     * @return
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver registerCommonsMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }

}
