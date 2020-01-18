package com.epam.preprod.tereshkevych.shop.web.servlet;

import com.epam.preprod.tereshkevych.shop.container.Cart;
import com.epam.preprod.tereshkevych.shop.db.entity.Product;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.service.ProductService;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Servlet for management shopping cart
 *
 * @author Vladyslav Tereshkevych
 */

@WebServlet("/cart")
public class ShoppingCart extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ShoppingCart.class);

    private ProductService productService;

    private static final String PRODUCT_SERVICE = "productService";

    private static final String CART = "cart";

    private static final String PARAMETER_PRODUCT_ID = "productId";

    private static final String ERROR_CANNOT_ADD_PRODUCT = "Cannot add product to cart!";

    private static final String PAGE__SHOP_CART = "/WEB-INF/jsp/product/cart.jsp";

    private static final String ERROR_FIELD_LOG = "errorMessage --> ";

    private static final String SERVLET_HANDLER_ERROR = "error";

    private static final String ERROR_APP_EXCEPTION = "appError";

    @Override
    public void init() {
        ServletContext context = getServletContext();
        productService = (ProductService) context.getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        try {
            int productId = Integer.parseInt(httpServletRequest.getParameter(PARAMETER_PRODUCT_ID));
            Cart cart = getCart(session);
            cart.remove(productService.getProductById(productId));
        } catch (DbException e) {
            handleError(session, httpServletResponse, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcherShopCart = httpServletRequest.getRequestDispatcher(PAGE__SHOP_CART);
        dispatcherShopCart.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        Cart cart = getCart(session);
        try {
            cart.add(getProduct(httpServletRequest));
        } catch (DbException e) {
            handleError(session, httpServletResponse, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        Cart cart = getCart(session);
        try {
            cart.reduce(getProduct(httpServletRequest));
        } catch (DbException e) {
            handleError(session, httpServletResponse, e.getMessage());
        }
    }

    private Product getProduct(HttpServletRequest httpServletRequest) throws DbException, IOException {
        String data;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()))) {
            data = reader.readLine();
        }
        Matcher m = Pattern.compile("\\d+").matcher(data);
        String strNumber = "";
        while (m.find()) {
            strNumber = m.group();
        }
        if (!NumberUtils.isNumber(strNumber)) {
            throw new DbException(ERROR_CANNOT_ADD_PRODUCT);
        }
        return productService.getProductById(Integer.parseInt(strNumber));
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART, cart);
        }
        return cart;
    }

    private void handleError(HttpSession session, HttpServletResponse httpServletResponse, String errorMessage) throws IOException {
        session.setAttribute(ERROR_APP_EXCEPTION, errorMessage);
        LOG.error(ERROR_FIELD_LOG + errorMessage);
        httpServletResponse.sendRedirect(SERVLET_HANDLER_ERROR);
    }
}