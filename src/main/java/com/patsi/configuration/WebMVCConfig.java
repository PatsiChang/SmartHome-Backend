package com.patsi.configuration;

import com.patsi.interceptors.CheckUserInterceptor;
import com.patsi.interceptors.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Autowired
    LoggingInterceptor loggingInterceptor;
    @Autowired
    CheckUserInterceptor checkUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
        registry.addInterceptor(checkUserInterceptor);
    }
}
