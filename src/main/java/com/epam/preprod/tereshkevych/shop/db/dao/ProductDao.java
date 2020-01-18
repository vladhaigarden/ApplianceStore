package com.epam.preprod.tereshkevych.shop.db.dao;

import com.epam.preprod.tereshkevych.shop.db.entity.Product;
import com.epam.preprod.tereshkevych.shop.exception.DaoException;
import com.epam.preprod.tereshkevych.shop.web.dto.FilterProductDto;

import java.util.List;
import java.util.Map;

public interface ProductDao extends GenericDao<Product> {

    List<Product> findProductsByFilter(Map<String, Object> containerConnection, FilterProductDto filterProductDto) throws DaoException;

    int getAmountProducts(Map<String, Object> container, FilterProductDto filterProductDto) throws DaoException;
}