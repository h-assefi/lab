package com.h.asefi.demo.common.annotation.apiDeprecated;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class ApiDeprecationInterceptor implements HandlerInterceptor {
    /**
     * Intercepts HTTP requests to check for the presence of the `ApiDeprecated`
     * annotation
     * on the handler method. If the annotation is present, adds headers to the
     * response
     * indicating that the API is deprecated. Optionally includes the "Since" and
     * "Link"
     * headers if specified in the annotation.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @param handler  the handler (or handler method) chosen to handle the request
     * @return true to continue processing the request
     */
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
