package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogSecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        String header = httpRequest.getHeader("xs-header");
        log.warn("New successfully authentication with header: " + header);
        chain.doFilter(request, response);
    }

}
