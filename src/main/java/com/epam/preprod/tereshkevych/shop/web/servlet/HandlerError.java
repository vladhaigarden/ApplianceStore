package com.epam.preprod.tereshkevych.shop.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for redirecting to error page
 *
 * @author Vladyslav Tereshkevych
 */

public class HandlerError extends HttpServlet {

    private static final String PAGE__ERROR_PAGE = "http://localhost:8888/app_error_page.jsp";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        httpServletResponse.sendRedirect(PAGE__ERROR_PAGE);
    }
}