package com.epam.preprod.tereshkevych.shop.service.impl;

import com.epam.preprod.tereshkevych.shop.db.dao.UserDao;
import com.epam.preprod.tereshkevych.shop.db.entity.User;
import com.epam.preprod.tereshkevych.shop.db.manager.TransactionManager;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * User service for communication with UserDao
 *
 * @author Vladyslav Tereshkevych
 */
public class UserServiceImpl implements UserService {

    private TransactionManager manager;
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao, TransactionManager manager) {
        this.userDao = userDao;
        this.manager = manager;
    }

    @Override
    public User getUserByLogin(String login) throws DbException {
        return manager.execute(container -> userDao.find(login, container));
    }

    @Override
    public User login(String login, String password) throws DbException {
        return manager.execute(container -> userDao.login(login, DigestUtils.md5Hex(password), container));
    }

    @Override
    public User addUser(User user) throws DbException {
        return manager.execute(container -> userDao.create(user, container));
    }
}