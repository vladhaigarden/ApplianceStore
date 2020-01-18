package com.epam.preprod.tereshkevych.shop.web.filter;

import com.epam.preprod.tereshkevych.shop.wrapper.GzipResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseCompressionFilter implements Filter {

    private static final String TEXT_TYPE = "text";

    private static final String NAME_HEADER_ENCODING = "accept-encoding";

    private static final String GZIP_TYPE_ENCODING = "gzip";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (canBeCompressed(servletRequest, servletResponse)) {
            GzipResponseWrapper gzipResponse = new GzipResponseWrapper(response);
            filterChain.doFilter(request, gzipResponse);
            gzipResponse.finish();
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean canBeCompressed(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String contentType = servletResponse.getContentType();
        String acceptEncoding = request.getHeader(NAME_HEADER_ENCODING);
        if (contentType.startsWith(TEXT_TYPE)) {
            return acceptEncoding != null && acceptEncoding.contains(GZIP_TYPE_ENCODING);
        }
        return false;
    }
}