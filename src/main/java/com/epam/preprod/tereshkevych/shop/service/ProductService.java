package com.epam.preprod.tereshkevych.shop.service;

import com.epam.preprod.tereshkevych.shop.db.entity.Category;
import com.epam.preprod.tereshkevych.shop.db.entity.Manufacturer;
import com.epam.preprod.tereshkevych.shop.db.entity.Product;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.web.dto.FilterProductDto;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts() throws DbException;

    Product getProductById(int id) throws DbException;

    List<Product> getProductsByFilter(FilterProductDto filterProductDto) throws DbException;

    int getAmountProducts(FilterProductDto filterProductDto) throws DbException;

    List<Category> getAllCategories() throws DbException;

    List<Manufacturer> getAllManufacturers() throws DbException;
}