package com.epam.preprod.tereshkevych.shop.service;

import com.epam.preprod.tereshkevych.shop.db.entity.Order;
import com.epam.preprod.tereshkevych.shop.exception.DbException;

public interface OrderService {

    Order addOrder(Order order) throws DbException;
}