package com.epam.preprod.tereshkevych.shop.web.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createProduct")
public class CreateProduct extends HttpServlet {

    private static final String PAGE_CREATE_PRODUCT = "/WEB-INF/jsp/product/create_product.jsp";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcherRegistrationPage = httpServletRequest.getRequestDispatcher(PAGE_CREATE_PRODUCT);
        dispatcherRegistrationPage.forward(httpServletRequest, httpServletResponse);
    }
}