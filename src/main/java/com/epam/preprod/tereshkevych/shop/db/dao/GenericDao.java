package com.epam.preprod.tereshkevych.shop.db.dao;

import com.epam.preprod.tereshkevych.shop.db.entity.Entity;
import com.epam.preprod.tereshkevych.shop.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface GenericDao<T extends Entity> {

    T create(T entity, Map<String, Object> containerConnection) throws DaoException;

    T find(String name, Map<String, Object> containerConnection) throws DaoException;

    List<T> findAll(Map<String, Object> containerConnection) throws DaoException;

    boolean update(T entity, Map<String, Object> containerConnection) throws DaoException;

    boolean delete(T entity, Map<String, Object> containerConnection) throws DaoException;
}