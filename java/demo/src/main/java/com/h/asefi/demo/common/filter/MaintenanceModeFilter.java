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