package com.david.spring.firstApp.security.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Before Filter");
        chain.doFilter(request, response);
        System.out.println("After Filter");
    }
}
