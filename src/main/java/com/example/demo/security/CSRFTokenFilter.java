package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;

@Slf4j
public class CSRFTokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object o = request.getAttribute("_csrf");
        CsrfToken csrfToken = (CsrfToken) o;
        log.info("======csrf token:=======");
        final String token = csrfToken.getToken();
        log.info(token);

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.addHeader("_csrf", token);

        chain.doFilter(request, response);
    }

}
