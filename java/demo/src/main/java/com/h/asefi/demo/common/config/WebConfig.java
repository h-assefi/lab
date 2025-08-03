package com.h.asefi.demo.common.config;

import com.h.asefi.demo.common.interceptor.DeprecatedEndpointLoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DeprecatedEndpointLoggerInterceptor());
    }
}
