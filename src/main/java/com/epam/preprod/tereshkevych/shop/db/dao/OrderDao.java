package com.epam.preprod.tereshkevych.shop.db.dao;

import com.epam.preprod.tereshkevych.shop.db.entity.Order;
import com.epam.preprod.tereshkevych.shop.exception.DaoException;

import java.util.Map;

public interface OrderDao extends GenericDao<Order> {

    Order addOrder(Order order, Map<String, Object> containerConnection) throws DaoException;
}