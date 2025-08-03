package com.h.asefi.demo.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class DeprecatedEndpointLoggerInterceptor implements HandlerInterceptor {
    private static final String DEPRECATED_HEADER = "Deprecated";
    private static final String LOG_FILE_PATH = "deprecated_endpoints.log";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String deprecatedHeader = request.getHeader(DEPRECATED_HEADER);

        if("true".equals(deprecatedHeader)) {
            logDeprecatedCall(request);
        }
        return true;
    }

    private void logDeprecatedCall(HttpServletRequest request) {
        String logEntry = String.format(
                "[%s] Deprecated API called: %s %s (Called by: %s)\n",
                LocalDateTime.now(),
                request.getMethod(),
                request.getRequestURI(),
                request.getHeader("User-Agent") // Or another header identifying the caller
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logEntry);
        } catch (IOException e) {
            System.err.println("Failed to log deprecated API call: " + e.getMessage());
        }
    }
}
