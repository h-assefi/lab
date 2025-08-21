package com.h.asefi.demo.common.helper;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestHelper {
    /**
     * Gets the client IP address from the request, taking into account load
     * balancers
     * and proxies using the X-Forwarded-For header.
     *
     * @return The client IP address or "unknown" if the request is invalid.
     */
    public static String getClientIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return "unknown";
        }

        HttpServletRequest request = attributes.getRequest();
        String header = request.getHeader("X-Forwarded-For");
        if (header != null && !header.isEmpty() && !"unknown".equalsIgnoreCase(header)) {
            return header.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
