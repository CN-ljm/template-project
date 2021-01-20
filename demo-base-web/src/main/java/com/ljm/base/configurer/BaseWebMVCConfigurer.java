package com.ljm.base.configurer;

import com.ljm.base.fifter.MyHttpServletFilter;
import com.ljm.base.interceptor.SignatureHandleInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class BaseWebMVCConfigurer implements WebMvcConfigurer {

    /*@Resource
    private MyHttpServletFilter myHttpServletFilter;*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SignatureHandleInterceptor()).addPathPatterns("/hello/**");
    }


    @Bean
    public FilterRegistrationBean registerFilter() {
//        System.out.println("BaseWebMVCConfigurer registerFilter");
        log.debug("BaseWebMVCConfigurer registerFilter");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyHttpServletFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        registration.setEnabled(true);
        return registration;
    }
}
