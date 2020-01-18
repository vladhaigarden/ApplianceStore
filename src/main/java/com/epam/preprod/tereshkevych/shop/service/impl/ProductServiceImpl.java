package com.epam.preprod.tereshkevych.shop.service.impl;

import com.epam.preprod.tereshkevych.shop.db.dao.CategoryDao;
import com.epam.preprod.tereshkevych.shop.db.dao.ManufacturerDao;
import com.epam.preprod.tereshkevych.shop.db.dao.ProductDao;
import com.epam.preprod.tereshkevych.shop.db.entity.Category;
import com.epam.preprod.tereshkevych.shop.db.entity.Manufacturer;
import com.epam.preprod.tereshkevych.shop.db.entity.Product;
import com.epam.preprod.tereshkevych.shop.db.manager.TransactionManager;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.service.ProductService;
import com.epam.preprod.tereshkevych.shop.web.dto.FilterProductDto;

import java.util.List;

/**
 * User service for communication with ProductDao
 *
 * @author Vladyslav Tereshkevych
 */
public class ProductServiceImpl implements ProductService {

    private TransactionManager manager;
    private ProductDao productDao;
    private CategoryDao categoryDao;
    private ManufacturerDao manufacturerDao;

    public ProductServiceImpl() {
    }

    public ProductServiceImpl(ProductDao productDao, CategoryDao categoryDao, ManufacturerDao manufacturerDao, TransactionManager manager) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
        this.manufacturerDao = manufacturerDao;
        this.manager = manager;
    }

    @Override
    public List<Product> getAllProducts() throws DbException {
        return manager.execute(container -> productDao.findAll(container));
    }

    @Override
    public Product getProductById(int id) throws DbException {
        return manager.execute(container -> productDao.find(String.valueOf(id), container));
    }

    @Override
    public List<Product> getProductsByFilter(FilterProductDto filterProductDto) throws DbException {
        return manager.execute(container -> productDao.findProductsByFilter(container, filterProductDto));
    }

    @Override
    public int getAmountProducts(FilterProductDto filterProductDto) throws DbException {
        return manager.execute(container -> productDao.getAmountProducts(container, filterProductDto));
    }

    @Override
    public List<Category> getAllCategories() throws DbException {
        return manager.execute(container -> categoryDao.findAll(container));
    }

    @Override
    public List<Manufacturer> getAllManufacturers() throws DbException {
        return manager.execute(container -> manufacturerDao.findAll(container));
    }
}