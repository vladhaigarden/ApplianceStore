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
 * Servlet for opening page of account
 *
 * @author Vladyslav Tereshkevych
 */
@WebServlet("/account")
public class UserAccount extends HttpServlet {

    private static final String PAGE__ACCOUNT_USER = "/WEB-INF/jsp/user/user_account.jsp";

    private static final String RES_ADD_USER = "New user has been successfully registered";

    private static final String USER = "user";

    private static final String MESSAGE = "message";

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        httpServletRequest.setAttribute(USER, session.getAttribute(USER));
        httpServletRequest.setAttribute(MESSAGE, RES_ADD_USER);
        RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(PAGE__ACCOUNT_USER);
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}