package com.h.asefi.demo.common.annotation.apiDeprecated;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiDeprecationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod method) {
            ApiDeprecated annotation = method.getMethodAnnotation(ApiDeprecated.class);
            if (annotation != null) {
                response.addHeader("Deprecated", "true");
                if (!annotation.since().isEmpty())
                    response.addHeader("Since", annotation.since());
                if (!annotation.link().isEmpty())
                    response.addHeader("Link", annotation.link());
            }
        }
        return true;
    }
}
