package com.h.asefi.demo.common.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityHeadersFilter extends OncePerRequestFilter {
    /**
     * Applies security headers to the HTTP response to enhance security.
     * 
     * This filter adds the following headers to the response:
     * - X-XSS-Protection: Enables cross-site scripting (XSS) filter in browsers.
     * - X-Content-Type-Options: Prevents MIME type sniffing.
     * - Content-Security-Policy: Restricts resources to be loaded from the same
     * origin.
     * 
     * @param request     the HTTP request
     * @param response    the HTTP response with security headers added
     * @param filterChain the filter chain for continuing the request
     * @throws IOException                      if an I/O error occurs during
     *                                          filtering
     * @throws jakarta.servlet.ServletException if an error occurs during filtering
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, jakarta.servlet.ServletException {
        response.setHeader("X-XSS-Protection", "1; mode=block");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("Content-Security-Policy", "default-src 'self'");
        filterChain.doFilter(request, response);
    }
}