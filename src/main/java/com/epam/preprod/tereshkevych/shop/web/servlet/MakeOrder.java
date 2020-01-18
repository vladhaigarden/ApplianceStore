package com.epam.preprod.tereshkevych.shop.web.servlet;

import com.epam.preprod.tereshkevych.shop.container.Cart;
import com.epam.preprod.tereshkevych.shop.db.entity.Order;
import com.epam.preprod.tereshkevych.shop.db.entity.OrderHistory;
import com.epam.preprod.tereshkevych.shop.db.entity.Product;
import com.epam.preprod.tereshkevych.shop.db.entity.User;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Servlet for processing of order
 *
 * @author Vladyslav Tereshkevych
 */

@WebServlet("/order")
public class MakeOrder extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ShoppingCart.class);

    private static final String RESULT_ADD_USER = "Thank you, your order has been successfully placed";

    private static final String PAGE__PRODUCT_ORDER = "/WEB-INF/jsp/product/order.jsp";

    private static final String PAGE__RESULT_PAGE = "http://localhost:8888/result_operation.jsp";

    private static final String ERROR_FIELD_LOG = "errorMessage --> ";

    private static final String SERVLET_HANDLER_ERROR = "error";

    private static final String RESULT_MAKE_ORDER = "result";

    private static final String ERROR_APP_EXCEPTION = "appError";

    private static final String ATTRIBUTE_CART = "cart";

    private static final String ATTRIBUTE_USER = "user";

    private static final String ORDER_SERVICE = "orderService";

    private OrderService orderService;

    @Override
    public void init() {
        orderService = (OrderService) getServletContext().getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        Order order = createOrder(session);
        session.removeAttribute(ATTRIBUTE_CART);
        try {
            orderService.addOrder(order);
            session.setAttribute(RESULT_MAKE_ORDER, RESULT_ADD_USER);
            httpServletResponse.sendRedirect(PAGE__RESULT_PAGE);
        } catch (DbException e) {
            handleError(session, httpServletResponse, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RequestDispatcher dispatcherShopCart = httpServletRequest.getRequestDispatcher(PAGE__PRODUCT_ORDER);
        dispatcherShopCart.forward(httpServletRequest, httpServletResponse);
    }

    private Order createOrder(HttpSession session) {
        Order order = new Order();
        Cart cart = (Cart) session.getAttribute(ATTRIBUTE_CART);
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        order.setUserId(user.getId());
        List<OrderHistory> orderHistories = new ArrayList<>();
        Map<Product, Integer> basket = cart.getBasket();
        for (Map.Entry<Product, Integer> pair : basket.entrySet()) {
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setQuantityProduct(pair.getValue());
            orderHistory.setProductId(pair.getKey().getId());
            orderHistory.setCurrentPrice(pair.getKey().getPrice());
            orderHistories.add(orderHistory);
        }
        order.setOrderHistory(orderHistories);
        return order;
    }

    private void handleError(HttpSession session, HttpServletResponse httpServletResponse, String errorMessage) throws IOException {
        session.setAttribute(ERROR_APP_EXCEPTION, errorMessage);
        LOG.error(ERROR_FIELD_LOG + errorMessage);
        httpServletResponse.sendRedirect(SERVLET_HANDLER_ERROR);
    }
}