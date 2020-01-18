package com.epam.preprod.tereshkevych.shop.web.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for exit from user account
 *
 * @author Vladyslav Tereshkevych
 */

@WebServlet("/logout")
public class LogoutUser extends HttpServlet {

    private static final String START_PAGE = "/index.jsp";

    private static final String ATTRIBUTE_USER = "user";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(ATTRIBUTE_USER);
        RequestDispatcher dispatcherStartPage = httpServletRequest.getRequestDispatcher(START_PAGE);
        dispatcherStartPage.forward(httpServletRequest, httpServletResponse);
    }
}