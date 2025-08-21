package com.h.asefi.demo.common.filter;

import com.h.asefi.demo.common.Strings;
import com.h.asefi.demo.status.StatusService;
import com.h.asefi.demo.status.dto.Status;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MaintenanceModeFilter implements Filter {

    @Autowired
    private StatusService statusService;

    /**
     * If the system is in maintenance mode, this filter will intercept all incoming
     * requests and return a JSON response with a status of 503.
     * If the system is not in maintenance mode, the filter will allow the request
     * to proceed as normal.
     * The only exception is for requests to the "/status/maintainable" endpoint,
     * which are always allowed to proceed, even if the system is in maintenance
     * mode.
     * This allows the system to be set in and out of maintenance mode without
     * blocking access to the maintenance endpoint.
     * 
     * @param request     the incoming request
     * @param response    the outgoing response
     * @param filterChain the filter chain to invoke the next filter in
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        if (uri.contains("/status/maintainable")) {
            filterChain.doFilter(request, response);
        } else {
            if (statusService.isMaintenanceMode()) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getWriter().write(String.format("""
                        {
                          "status":"%s",
                          "message":"%s"
                        }
                        """, Status.MAINTENANCE, Strings.UnderMaintenance));
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}