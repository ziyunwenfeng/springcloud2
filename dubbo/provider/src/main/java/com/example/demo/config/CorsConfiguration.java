package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 跨域过滤器配置类
 *
 * @author wenfeng
 * @date 2020年 05月25日 14:48:54
 */
@Configuration
public class CorsConfiguration {
    @Bean
    public FilterRegistrationBean<CorsFilter> coreFilter() {
        FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CorsFilter());
        filterRegistrationBean.setName("CorsFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter2> coreFilter2() {
        FilterRegistrationBean<CorsFilter2> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CorsFilter2());
        filterRegistrationBean.setName("CorsFilter2");
        filterRegistrationBean.addUrlPatterns("/test/*");
        filterRegistrationBean.setOrder(6);
        return filterRegistrationBean;
    }
}
