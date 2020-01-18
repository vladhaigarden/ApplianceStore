package com.epam.preprod.tereshkevych.shop.service;

import com.epam.preprod.tereshkevych.shop.db.entity.User;
import com.epam.preprod.tereshkevych.shop.exception.DbException;

public interface UserService {

    User getUserByLogin(String login) throws DbException;

    User login(String login, String password) throws DbException;

    User addUser(User user) throws DbException;
}