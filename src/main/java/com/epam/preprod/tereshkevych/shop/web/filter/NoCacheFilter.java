package com.epam.preprod.tereshkevych.shop.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class NoCacheFilter implements Filter {

    private final Map<String, String> httpParameters = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        Enumeration parameters = filterConfig.getInitParameterNames();
        while (parameters.hasMoreElements()) {
            String name = (String) parameters.nextElement();
            String value = filterConfig.getInitParameter(name);
            httpParameters.put(name, value);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        httpParameters.forEach(response::setHeader);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}