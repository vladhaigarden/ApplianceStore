package com.epam.preprod.tereshkevych.shop.service.impl;

import com.epam.preprod.tereshkevych.shop.db.dao.OrderDao;
import com.epam.preprod.tereshkevych.shop.db.entity.Order;
import com.epam.preprod.tereshkevych.shop.db.manager.TransactionManager;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private TransactionManager manager;
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao, TransactionManager manager) {
        this.orderDao = orderDao;
        this.manager = manager;
    }

    @Override
    public Order addOrder(Order order) throws DbException {
        return manager.execute(container -> orderDao.addOrder(order, container));
    }
}