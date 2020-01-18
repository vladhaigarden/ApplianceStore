package com.epam.preprod.tereshkevych.shop.db.dao;

import com.epam.preprod.tereshkevych.shop.db.entity.User;
import com.epam.preprod.tereshkevych.shop.exception.DaoException;

import java.util.Map;

public interface UserDao extends GenericDao<User> {

    User login(String login, String password, Map<String, Object> containerConnection) throws DaoException;
}