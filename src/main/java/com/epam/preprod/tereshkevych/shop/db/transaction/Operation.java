package com.epam.preprod.tereshkevych.shop.db.transaction;

import com.epam.preprod.tereshkevych.shop.exception.DaoException;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface Operation<T> {

    T execute(Map<String, Object> containerConnection) throws DaoException, NoSuchAlgorithmException;
}